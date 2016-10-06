package BD_DAOs;

import java.sql.SQLException;

import BD_Basicos.MeuPreparedStatement;
import BD_Basicos.MeuResultSet;
import BD_DBOs.Cliente_DBO;

public class Cliente_DAO 
{
	protected MeuPreparedStatement bd;
	
	//-------------------------------------------------------------------------------------------------------------------------------//
    //-------------------------------------------------------------Construtor--------------------------------------------------------//
    //-------------------------------------------------------------------------------------------------------------------------------//
	
	public Cliente_DAO(MeuPreparedStatement _novoBd) throws Exception
	{
		if (_novoBd == null) throw new Exception("BD Inv�lido");
		this.bd = _novoBd;
	}
	
	//-----------------------------------------------------------------------------------------------------------------------------------//
    //---------------------------------------------------------M�todos de Getters--------------------------------------------------------//
    //-----------------------------------------------------------------------------------------------------------------------------------//
	
    public Cliente_DBO getCliente_DBO(int _codClien) throws Exception
    {
    	if (_codClien <= 0 || !this.isCadastrado(_codClien)) throw new Exception ("Cliente N�o Existe");
    	
    	Cliente_DBO cliente = null;

        try
        {
            String sql = "select * from ClientePG where codCliente=?";

            bd.prepareStatement (sql);
            bd.setInt (1, _codClien);

            MeuResultSet result = (MeuResultSet)bd.executeQuery ();
            cliente = new Cliente_DBO(result.getInt("codCliente"), result.getString("nomeCliente"), 
            		                  result.getString("emailCliente"), result.getString("telefone"));
        }
        catch (SQLException erro){throw new Exception ("Erro ao procurar o cliente");}

        return cliente;
    }

    public MeuResultSet getCliente_DAO() throws Exception
    {
        MeuResultSet result = null;

        try
        {
            String sql = "select * from ClientePG";

            bd.prepareStatement (sql);
            result = (MeuResultSet)bd.executeQuery ();
        }
        catch (SQLException erro){throw new Exception ("Erro ao recuperar a tabela Cliente");}

        return result;
    }
	
    //-----------------------------------------------------------------------------------------------------------------------------------//
    //------------------------------------------------------------M�todos CRUD-----------------------------------------------------------//
    //-----------------------------------------------------------------------------------------------------------------------------------//
    
	public void incluirClien (Cliente_DBO _cliente) throws Exception
    {
        if (_cliente == null) throw new Exception ("Cliente Inv�lido");
        if (this.isCadastrado(_cliente.getCodClien())) throw new Exception ("Cliente J� Cadastrado");

        try
        {
            String sql = "insert into ClientePG values(?,?,?,?)";

            bd.prepareStatement (sql);
            bd.setInt(1, _cliente.getCodClien());
            bd.setString(2, _cliente.getNomeClien());
            bd.setString(3, _cliente.getEmailClien());
            bd.setString(4, _cliente.getTeleClien());

            bd.executeUpdate();
            bd.commit();
        }
        catch (SQLException erro)
        {
        	bd.rollback();
            throw new Exception ("Erro ao inserir dados do cliente");
        }
    }

    public void excluirClien (int _codClien) throws Exception
    {
    	if (_codClien <= 0) throw new Exception ("Cliente Inv�lido");
        if (!this.isCadastrado(_codClien)) throw new Exception ("Cliente N�o Cadastrado");

        try
        {
            String sql = "delete from ClientePG where codCliente=?";

            bd.prepareStatement(sql);
            bd.setInt (1, _codClien);

            bd.executeUpdate();
            bd.commit();
        }
        catch (SQLException erro)
        {
        	bd.rollback();
            throw new Exception ("Erro ao excluir dados do cliente");
        }
    }

    public void alterarClien (Cliente_DBO _cliente) throws Exception
    {
    	if (_cliente == null || !this.isCadastrado(_cliente.getCodClien())) throw new Exception ("Cliente N�o Existe");

        try
        {
            String sql = "update ClientePG set nomeCliente=?, emailCliente=?, telefone=? where codCliente=?";

            bd.prepareStatement (sql);
            bd.setString(1, _cliente.getNomeClien());
            bd.setString(2, _cliente.getEmailClien());
            bd.setString(3, _cliente.getTeleClien());
            bd.setInt(4, _cliente.getCodClien());

            bd.executeUpdate ();
            bd.commit();
        }
        catch (SQLException erro)
        {
        	bd.rollback();
            throw new Exception ("Erro ao atualizar dados do cliente");
        }
    }
    
    //-----------------------------------------------------------------------------------------------------------------------------------//
    //---------------------------------------------------------M�todo Auxiliar-----------------------------------------------------------//
    //-----------------------------------------------------------------------------------------------------------------------------------//
    
    public boolean isCadastrado (int _codClien) throws Exception
    {
        boolean achou = false;

        try
        {
            String sql = "select * from ClientePG where codCliente=?";

            bd.prepareStatement(sql);
            bd.setInt(1, _codClien);

            MeuResultSet result = (MeuResultSet)bd.executeQuery();
            achou = result.first();
        }
        catch (SQLException erro){throw new Exception ("Erro ao procurar o cliente");}

        return achou;
    }
}