package BD_DAOs;

import java.sql.SQLException;

import BD_Basicos.MeuPreparedStatement;
import BD_Basicos.MeuResultSet;
import BD_DBOs.Cliente_DBO;
import Tipos.Elemento;
import Tipos.ListaDupla;

public class Cliente_DAO 
{
	protected MeuPreparedStatement bd;
	protected String sql;
	
	//-------------------------------------------------------------------------------------------------------------------------------//
    //-------------------------------------------------------------Construtor--------------------------------------------------------//
    //-------------------------------------------------------------------------------------------------------------------------------//
	
	public Cliente_DAO(MeuPreparedStatement _novoBd) throws Exception
	{
		if (_novoBd == null) throw new Exception("BD Inválido");
		this.bd = _novoBd;
		this.sql = "select * from ClientePG ";
	}
	
	//-----------------------------------------------------------------------------------------------------------------------------------//
    //---------------------------------------------------------Métodos de Getters--------------------------------------------------------//
    //-----------------------------------------------------------------------------------------------------------------------------------//
	
    public Cliente_DBO getCliente_DBO(Cliente_DBO _clien) throws Exception
    {
    	if (_clien == null || !this.isCadastrado(_clien)) throw new Exception ("Cliente Não Existe");
    	
    	Cliente_DBO cliente = null;

        try
        {
            String sql = "select * from ClientePG where codCliente=?";

            bd.prepareStatement (sql);
            bd.setInt (1, _clien.getCodClien());

            MeuResultSet result = (MeuResultSet)bd.executeQuery ();
            result.next();
            
            cliente = new Cliente_DBO(result.getInt("codCliente"), result.getString("nomeCliente"), 
            		                  result.getString("emailCliente"), result.getString("telefone"));
        }
        catch (SQLException erro){throw new Exception ("Erro ao procurar o cliente");}

        return cliente;
    }

    public MeuResultSet getCliente_DAO(String _ordenar) throws Exception
    {
        MeuResultSet result = null;

        try
        {
            bd.prepareStatement (this.sql + _ordenar);
            result = (MeuResultSet)bd.executeQuery();
        }
        catch (SQLException erro){throw new Exception ("Erro ao recuperar a tabela Cliente");}

        return result;
    }
    
    public Cliente_DBO getFirst(String _ordenar) throws Exception
    {
    	MeuResultSet result = this.getCliente_DAO(_ordenar);
        result.next();
        
        return new Cliente_DBO(result.getInt("codCliente"), result.getString("nomeCliente"), 
                               result.getString("emailCliente"), result.getString("telefone"));
    }
    
    public Cliente_DBO getLast(String _ordenar) throws Exception
    {
    	MeuResultSet result = this.getCliente_DAO(_ordenar);
        result.afterLast();
        result.previous();
        
        return new Cliente_DBO(result.getInt("codCliente"), result.getString("nomeCliente"), 
                               result.getString("emailCliente"), result.getString("telefone"));
    }
    
    public Cliente_DBO getProx(int _codClien, String _ordenar) throws Exception
    {
    	MeuResultSet result = this.getCliente_DAO(_ordenar);
        result.next();
        
        while (!result.isLast())
        {
	        if (result.getInt("codCliente") == _codClien) 
	        {
	        	result.next();
	        	return new Cliente_DBO(result.getInt("codCliente"), result.getString("nomeCliente"), 
		                               result.getString("emailCliente"), result.getString("telefone"));
	        }
	        else result.next();
        }
        return null;
    }
    
    public Cliente_DBO getAnt(int _codClien, String _ordenar) throws Exception
    {
    	MeuResultSet result = this.getCliente_DAO(_ordenar);
        result.next();
        
        while (!result.isAfterLast())
        {
	        if (result.getInt("codCliente") == _codClien) 
	        {
	        	result.previous();
	        	return new Cliente_DBO(result.getInt("codCliente"), result.getString("nomeCliente"), 
		                               result.getString("emailCliente"), result.getString("telefone"));
	        }
	        else result.next();
        }
        return null;
    }
	
    //-----------------------------------------------------------------------------------------------------------------------------------//
    //------------------------------------------------------------Métodos CRUD-----------------------------------------------------------//
    //-----------------------------------------------------------------------------------------------------------------------------------//
    
	public void incluirClien (Cliente_DBO _cliente) throws Exception
    {
        if (_cliente == null) throw new Exception ("Cliente Inválido");
        if (this.isCadastrado(_cliente)) throw new Exception ("Cliente Já Cadastrado");
        
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

    public void excluirClien (Cliente_DBO _cliente) throws Exception
    {
    	if (_cliente.getCodClien() <= 0) throw new Exception ("Cliente Inválido");
        if (!this.isCadastrado(_cliente)) throw new Exception ("Cliente Não Cadastrado");

        try
        {
            String sql = "delete from ClientePG where codCliente=?";

            bd.prepareStatement(sql);
            bd.setInt (1, _cliente.getCodClien());

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
    	if (_cliente == null || this.isCadastrado(_cliente)) throw new Exception ("Cliente Não Existe");

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
    //---------------------------------------------------------Método Auxiliar-----------------------------------------------------------//
    //-----------------------------------------------------------------------------------------------------------------------------------//
    
    public boolean isCadastrado (Cliente_DBO _clien) throws Exception
    {
    	try
        {
            String sql = "select * from ClientePG where nomeCliente=? and emailCliente=? and telefone=?";

            bd.prepareStatement(sql);
            bd.setString(1, _clien.getNomeClien());
            bd.setString(2, _clien.getEmailClien());
            bd.setString(3, _clien.getTeleClien());

            MeuResultSet result = (MeuResultSet)bd.executeQuery();
            result.next();
            
            if (result.getString("nomeCliente") != null) return true;
        }
        catch (SQLException erro){return false;}

        return false;
    }

	public ListaDupla<Cliente_DBO> getClientes(String _nome, String _email, String _tel) throws Exception 
	{
		int i = 1;
		String sql = "select * from ClientePG where";
		boolean podeNome = false, podeEmail = false, podeTel = false;
		ListaDupla<Cliente_DBO> clien = new ListaDupla<Cliente_DBO>();
		
		try
        {
			if (_nome != null && !_nome.equals("")) 
			{
				_nome = "%" + _nome + "%";
				sql += " nomeCliente like ?";
				podeNome = true;
			}
			if (_email != null && !_email.equals("")) 
			{
				_email = "%" + _email + "%";
				if (podeNome)
					sql += " and emailCliente like ?";
				else
					sql += " emailCliente like ?";
				podeEmail = true;
			}
			if (_tel != null && !_tel.equals("")) 
			{
				_tel = "%" + _tel + "%";
				if (podeNome || podeEmail)
					sql += " and telefone like ?";
				else
					sql += " telefone like ?";
				podeTel = true;
			}

			bd.prepareStatement(sql);
            if (podeNome) bd.setString(i++, _nome);
            if (podeEmail) bd.setString(i++, _email);
            if (podeTel) bd.setString(i, _tel);

            MeuResultSet result = (MeuResultSet)bd.executeQuery();
            result.next();
            
            if (result.getString("nomeCliente") == null || result.getString("nomeCliente").equals("")) return null;
            while (!result.isAfterLast())
            {
            	clien.incluirNoFim(new Elemento<Cliente_DBO>(new Cliente_DBO(result.getInt("codCliente"), result.getString("nomeCliente"), 
	                                                                         result.getString("emailCliente"), result.getString("telefone"))));
            	result.next();
            }
        }
        catch (SQLException erro){return null;}

        return clien;
	}
}
