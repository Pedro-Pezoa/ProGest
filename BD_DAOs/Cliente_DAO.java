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
		if (_novoBd == null) throw new Exception("BD Inválido");
		this.bd = _novoBd;
	}
	
	//-----------------------------------------------------------------------------------------------------------------------------------//
    //---------------------------------------------------------Métodos de Getters--------------------------------------------------------//
    //-----------------------------------------------------------------------------------------------------------------------------------//
	
    public Cliente_DBO getCliente_DBO(int _codClien) throws Exception
    {
    	if (_codClien <= 0 || !this.isCadastrado(this.getCliente_DBO(_codClien))) throw new Exception ("Cliente Não Existe");
    	
    	Cliente_DBO cliente = null;

        try
        {
            String sql = "select * from ClientePG where codCliente=?";

            bd.prepareStatement (sql);
            bd.setInt (1, _codClien);

            MeuResultSet result = (MeuResultSet)bd.executeQuery ();
            result.next();
            
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
    
    public Cliente_DBO getFirst() throws Exception
    {
    	MeuResultSet result = this.getCliente_DAO();
        result.next();
        
        return new Cliente_DBO(result.getInt("codCliente"), result.getString("nomeCliente"), 
                               result.getString("emailCliente"), result.getString("telefone"));
    }
    
    public Cliente_DBO getLast() throws Exception
    {
    	MeuResultSet result = this.getCliente_DAO();
        result.afterLast();
        result.previous();
        
        return new Cliente_DBO(result.getInt("codCliente"), result.getString("nomeCliente"), 
                               result.getString("emailCliente"), result.getString("telefone"));
    }
    
    public Cliente_DBO getProx(int _codClien) throws Exception
    {
    	MeuResultSet result = this.getCliente_DAO();
        result.next();
        
        while (!result.isLast())
        {
	        if (result.getInt("codCliente") == _codClien) 
	        {
	        	result.next();
	        	return new Cliente_DBO(result.getInt("codCliente"), result.getString("nomeCliente"), 
	                                   result.getString("emailCliente"), result.getString("telefone"));
	        }
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
    	if (_cliente == null || !this.isCadastrado(_cliente)) throw new Exception ("Cliente Não Existe");

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
        boolean achou = false;

        try
        {
            String sql = "select * from ClientePG where nomeCliente=? and emailCliente=? and telefone=?";

            bd.prepareStatement(sql);
            bd.setString(1, _clien.getNomeClien());
            bd.setString(2, _clien.getEmailClien());
            bd.setString(3, _clien.getTeleClien());

            MeuResultSet result = (MeuResultSet)bd.executeQuery();
            
            achou = result.first();
        }
        catch (SQLException erro){throw new Exception ("Erro ao procurar o cliente");}

        return achou;
    }
}
