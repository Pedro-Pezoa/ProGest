package BD_DAOs;

import java.sql.SQLException;

import BD_Basicos.MeuPreparedStatement;
import BD_Basicos.MeuResultSet;
import BD_Basicos.Utils;
import BD_DBOs.Atendimento_DBO;

public class Atendimento_DAO 
{
	protected MeuPreparedStatement bd;
	
	//-------------------------------------------------------------------------------------------------------------------------------//
    //-------------------------------------------------------------Construtor--------------------------------------------------------//
    //-------------------------------------------------------------------------------------------------------------------------------//
	
	public Atendimento_DAO(MeuPreparedStatement _novoBd) throws Exception
	{
		if (_novoBd == null) throw new Exception("BD Inválido");
		this.bd = _novoBd;
	}
	
	//-----------------------------------------------------------------------------------------------------------------------------------//
    //---------------------------------------------------------Métodos de Getters--------------------------------------------------------//
    //-----------------------------------------------------------------------------------------------------------------------------------//
	
    public Atendimento_DBO getAtendimento_DBO(int _codAten) throws Exception
    {
    	if (_codAten <= 0 || !this.isCadastrado(_codAten)) throw new Exception ("Atendimento Não Existe");
    	
    	Atendimento_DBO atend = null;

        try
        {
            String sql = "select * from AtendimentoPG where codAtendimento=?";

            bd.prepareStatement(sql);
            bd.setInt(1, _codAten);

            MeuResultSet result = (MeuResultSet)bd.executeQuery ();
            atend = new Atendimento_DBO(result.getInt("codAtendimento"), result.getInt("codCliente"), result.getString("nomeAtendente"), 
            		                    result.getString("dataAtendimento"), result.getString("tipoAtendimento"), result.getString("observacaoCliente"),
            		                    result.getString("statusAtendimento"));
        }
        catch (SQLException erro){throw new Exception ("Erro ao procurar o atendimento");}

        return atend;
    }

    public MeuResultSet getCliente_DAO() throws Exception
    {
        MeuResultSet result = null;

        try
        {
            String sql = "select * from AtendimentoPG";

            bd.prepareStatement(sql);
            result = (MeuResultSet)bd.executeQuery ();
        }
        catch (SQLException erro){throw new Exception ("Erro ao recuperar a tabela Atendimento");}

        return result;
    }
	
    //-----------------------------------------------------------------------------------------------------------------------------------//
    //------------------------------------------------------------Métodos CRUD-----------------------------------------------------------//
    //-----------------------------------------------------------------------------------------------------------------------------------//
    
	public void incluirAten (Atendimento_DBO _aten) throws Exception
    {
        if (_aten == null) throw new Exception ("Atendimento Inválido");
        if (this.isCadastrado(_aten.getCodAten())) throw new Exception ("Atendimento Já Cadastrado");

        try
        {
            String sql = "insert into AtendimentoPG values(?,?,?,?,?,?,?)";

            bd.prepareStatement (sql);
            bd.setInt(1, _aten.getCodAten());
            bd.setInt(2, _aten.getCodClien());
            bd.setString(3, _aten.getNomeAten());
            bd.setString(4, Utils.dateToString(_aten.getDataAten()));
            bd.setString(5, _aten.getTipoeAten());
            bd.setString(6, _aten.getObsClien());
            bd.setString(7, _aten.getStatusAten());

            bd.executeUpdate();
            bd.commit();
        }
        catch (SQLException erro)
        {
        	bd.rollback();
            throw new Exception ("Erro ao inserir dados do atendimento");
        }
    }

    public void excluirAten (int _codAten) throws Exception
    {
    	if (_codAten <= 0) throw new Exception ("Atendimento Inválido");
        if (!this.isCadastrado(_codAten)) throw new Exception ("Atendimento Não Cadastrado");

        try
        {
            String sql = "delete from AtendimentoPG where codAtendimento=?";

            bd.prepareStatement(sql);
            bd.setInt (1, _codAten);

            bd.executeUpdate();
            bd.commit();
        }
        catch (SQLException erro)
        {
        	bd.rollback();
            throw new Exception ("Erro ao excluir dados do atendimento");
        }
    }

    public void alterarAten (Atendimento_DBO _aten) throws Exception
    {
    	if (_aten == null || !this.isCadastrado(_aten.getCodAten())) throw new Exception ("Atendimento Não Existe");

        try
        {
            String sql = "update AtendimentoPG set codCliente=?, nomeAtendente=?, dataAtendimento=?, tipoAtendimento=?," +
            		     "observacaoCliente=?, statusAtendimento=? where codAtendimento=?";

            bd.prepareStatement (sql);
            bd.setInt(1, _aten.getCodClien());
            bd.setString(2, _aten.getNomeAten());
            bd.setString(3, Utils.dateToString(_aten.getDataAten()));
            bd.setString(4, _aten.getTipoeAten());
            bd.setString(5, _aten.getObsClien());
            bd.setString(6, _aten.getStatusAten());
            bd.setInt(7, _aten.getCodAten());

            bd.executeUpdate ();
            bd.commit();
        }
        catch (SQLException erro)
        {
        	bd.rollback();
            throw new Exception ("Erro ao atualizar dados do atendimento");
        }
    }
    
    //-----------------------------------------------------------------------------------------------------------------------------------//
    //---------------------------------------------------------Método Auxiliar-----------------------------------------------------------//
    //-----------------------------------------------------------------------------------------------------------------------------------//
    
    public boolean isCadastrado (int _codAten) throws Exception
    {
        boolean achou = false;

        try
        {
            String sql = "select * from AtendimentoPG where codAtendimento=?";

            bd.prepareStatement(sql);
            bd.setInt(1, _codAten);

            MeuResultSet result = (MeuResultSet)bd.executeQuery();
            achou = result.first();
        }
        catch (SQLException erro){throw new Exception ("Erro ao procurar o atendimento");}

        return achou;
    }
}
