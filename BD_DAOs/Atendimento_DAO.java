package BD_DAOs;

import java.sql.SQLException;

import BD_Basicos.MeuPreparedStatement;
import BD_Basicos.MeuResultSet;
import BD_Basicos.Utils;
import BD_DBOs.Atendimento_DBO;
import BD_DBOs.Cliente_DBO;
import Tipos.Elemento;
import Tipos.ListaDupla;

public class Atendimento_DAO 
{
	protected MeuPreparedStatement bd;
	protected String sql;
	
	//-------------------------------------------------------------------------------------------------------------------------------//
    //-------------------------------------------------------------Construtor--------------------------------------------------------//
    //-------------------------------------------------------------------------------------------------------------------------------//
	
	public Atendimento_DAO(MeuPreparedStatement _novoBd) throws Exception
	{
		if (_novoBd == null) throw new Exception("BD Inválido");
		this.bd = _novoBd;
		this.sql = "select * from AtendimentoPG ";
	}
	
	//-----------------------------------------------------------------------------------------------------------------------------------//
    //---------------------------------------------------------Métodos de Getters--------------------------------------------------------//
    //-----------------------------------------------------------------------------------------------------------------------------------//
	
    public Atendimento_DBO getAtendimento_DBO(Atendimento_DBO _aten) throws Exception
    {
    	if (_aten.getCodAten() <= 0 || !this.isCadastrado(_aten)) throw new Exception ("Atendimento Não Existe");
    	
    	Atendimento_DBO atend = null;

        try
        {
            String sql = "select * from AtendimentoPG where codAtendimento=?";

            bd.prepareStatement(sql);
            bd.setInt(1, _aten.getCodAten());

            MeuResultSet result = (MeuResultSet)bd.executeQuery ();
            result.next();
            
            atend = new Atendimento_DBO(result.getInt("codAtendimento"), 
            		result.getInt("codCliente"), 
            		result.getString("nomeAtendente"), 
            		                    result.getString("dataAtendimento"), 
            		                    result.getString("tipoAtendimento"), 
            		                    result.getString("observacaoCliente"),
            		                    result.getString("statusAtendimento"));
        }
        catch (SQLException erro){throw new Exception ("Erro ao procurar o atendimento");}

        return atend;
    }

    public MeuResultSet getAtendimento_DAO(String _ordenar) throws Exception
    {
        MeuResultSet result = null;

        try
        {
            bd.prepareStatement(this.sql + _ordenar);
            result = (MeuResultSet)bd.executeQuery();
        }
        catch (SQLException erro){throw new Exception ("Erro ao recuperar a tabela Atendimento");}

        return result;
    }
    
    public Atendimento_DBO getFirst(String _ordenar) throws Exception
    {
    	MeuResultSet result = this.getAtendimento_DAO(_ordenar);
        result.next();
        
        return new Atendimento_DBO(result.getInt("codAtendimento"), result.getInt("codCliente"), result.getString("nomeAtendente"), 
                                   Utils.dateToString(result.getString("dataAtendimento")), result.getString("tipoAtendimento"), result.getString("observacaoCliente"), 
                                   result.getString("statusAtendimento"));
    }
    
    public Atendimento_DBO getLast(String _ordenar) throws Exception
    {
    	MeuResultSet result = this.getAtendimento_DAO(_ordenar);
        result.afterLast();
        result.previous();
        
        return new Atendimento_DBO(result.getInt("codAtendimento"), result.getInt("codCliente"), result.getString("nomeAtendente"), 
        			 			   result.getString("dataAtendimento"), result.getString("tipoAtendimento"), result.getString("observacaoCliente"), 
        						   result.getString("statusAtendimento"));
    }
    
    public Atendimento_DBO getProx(int _codAten, String _ordenar) throws Exception
    {
    	MeuResultSet result = this.getAtendimento_DAO(_ordenar);
        result.next();
        
        while (!result.isLast())
        {
	        if (result.getInt("codAtendimento") == _codAten) 
	        {
	        	result.next();
	        	return new Atendimento_DBO(result.getInt("codAtendimento"), result.getInt("codCliente"), result.getString("nomeAtendente"), 
			 			   				   result.getString("dataAtendimento"), result.getString("tipoAtendimento"), result.getString("observacaoCliente"), 
			 			   				   result.getString("statusAtendimento"));
	        }
	        else result.next();
        }
        return null;
    }
    
    public Atendimento_DBO getAnt(int _codAten, String _ordenar) throws Exception
    {
    	MeuResultSet result = this.getAtendimento_DAO(_ordenar);
        result.next();
        
        while (!result.isLast())
        {
	        if (result.getInt("codCliente") == _codAten) 
	        {
	        	result.previous();
	        	return new Atendimento_DBO(result.getInt("codAtendimento"), result.getInt("codCliente"), result.getString("nomeAtendente"), 
		   				   				   result.getString("dataAtendimento"), result.getString("tipoAtendimento"), result.getString("observacaoCliente"), 
		   				   				   result.getString("statusAtendimento"));
	        }
	        else result.next();
        }
        if (result.isLast()) 
        {
        	result.previous();
        	return new Atendimento_DBO(result.getInt("codAtendimento"), result.getInt("codCliente"), result.getString("nomeAtendente"), 
	   				   			       result.getString("dataAtendimento"), result.getString("tipoAtendimento"), result.getString("observacaoCliente"), 
	   				   			       result.getString("statusAtendimento"));
        }
        return null;
    }
	
    //-----------------------------------------------------------------------------------------------------------------------------------//
    //------------------------------------------------------------Métodos CRUD-----------------------------------------------------------//
    //-----------------------------------------------------------------------------------------------------------------------------------//
    
	public void incluirAten (Atendimento_DBO _aten) throws Exception
    {
        if (_aten == null) throw new Exception ("Atendimento Inválido");
        if (this.isCadastrado(_aten)) throw new Exception ("Atendimento Já Cadastrado");

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

    public void excluirAten (Atendimento_DBO _aten) throws Exception
    {
    	if (_aten.getCodAten() <= 0) throw new Exception ("Atendimento Inválido");
        if (!this.isCadastrado(_aten)) throw new Exception ("Atendimento Não Cadastrado");

        try
        {
            String sql = "delete from AtendimentoPG where codAtendimento=?";

            bd.prepareStatement(sql);
            bd.setInt (1, _aten.getCodAten());

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
    	if (_aten == null || !this.isCadastrado(_aten)) throw new Exception ("Atendimento Não Existe");

        try
        {
            String sql = "update AtendimentoPG set codCliente=?, nomeAtendente=?, dataAtendimento=?, tipoAtendimento=?, " +
            		     "observacaoCliente=?, statusAtendimento=? where codAtendimento=?";

            bd.prepareStatement (sql);
            bd.setInt(1, _aten.getCodClien());
            bd.setString(2, _aten.getNomeAten());
            bd.setString(3, _aten.getDataAten());
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
    
    public boolean isCadastrado (Atendimento_DBO _aten) throws Exception
    {
        boolean achou = false;

        try
        {
            String sql = "select * from AtendimentoPG where codAtendimento=?";

            bd.prepareStatement(sql);
            bd.setInt(1, _aten.getCodAten());

            MeuResultSet result = (MeuResultSet)bd.executeQuery();
            achou = result.first();
        }
        catch (SQLException erro){throw new Exception ("Erro ao procurar o atendimento");}

        return achou;
    }
    
    public ListaDupla<Atendimento_DBO> getAtendimentos(String _nome, String _data, String _tipo) throws Exception 
	{
		int i = 1;
		String sql = "select * from AtendimentoPG where";
		boolean podeNome = false, podeData = false, podeTipo = false;
		ListaDupla<Atendimento_DBO> aten = new ListaDupla<Atendimento_DBO>();
		
		try
        {
			if (_nome != null && !_nome.equals("")) 
			{
				_nome = "%" + _nome + "%";
				sql += " nomeAtendente like ?";
				podeNome = true;
			}
			if (_data != null && !_data.equals("")) 
			{
				_data = "%" + _data + "%";
				if (podeNome)
					sql += " and dataAtendimento like ?";
				else
					sql += " dataAtendimento like ?";
				podeData = true;
			}
			if (_tipo != null && !_tipo.equals("")) 
			{
				_tipo = "%" + _tipo + "%";
				if (podeNome || podeData)
					sql += " and tipoAtendimento like ?";
				else
					sql += " tipoAtendimento like ?";
				podeTipo = true;
			}

			bd.prepareStatement(sql);
            if (podeNome) bd.setString(i++, _nome);
            if (podeData) bd.setString(i++, _data);
            if (podeTipo) bd.setString(i, _tipo);

            MeuResultSet result = (MeuResultSet)bd.executeQuery();
            result.next();
            
            if (result.getString("nomeAtendente") == null || result.getString("nomeAtendente").equals("")) return null;
            while (!result.isAfterLast())
            {
            	aten.incluirNoFim(new Elemento<Atendimento_DBO>(new Atendimento_DBO(result.getInt("codAtendimento"), result.getInt("codCliente"), 
            																	    result.getString("nomeAtendente"), Utils.dateToString(result.getString("dataAtendimento")), 
            																	    result.getString("tipoAtendimento"), result.getString("observacaoCliente"),
            																	    result.getString("statusAtendimento"))));
            	result.next();
            }
        }
        catch (SQLException erro){return null;}

        return aten;
	}
}
