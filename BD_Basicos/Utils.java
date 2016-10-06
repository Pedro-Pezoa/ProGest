package BD_Basicos;

import BD_DAOs.Atendimento_DAO;
import BD_DAOs.Cliente_DAO;

public class Utils 
{
	protected final MeuPreparedStatement BD = new MeuPreparedStatement("com.microsoft.sqlserver.jdbc.SQLServerDriver",
			                                                           "jdbc:sqlserver://regulus:1433;databasename=BDPP15190",
                                                                       "BDPP15190", "nada123");
	protected static Cliente_DAO clien;
	protected static Atendimento_DAO aten;
	
	//-------------------------------------------------------------------------------------------------------------------------------//
    //-------------------------------------------------------------Construtor--------------------------------------------------------//
    //-------------------------------------------------------------------------------------------------------------------------------//
	
	public Utils() throws Exception
	{
		clien = new Cliente_DAO(BD);
		aten = new Atendimento_DAO(BD);
	}

	//-----------------------------------------------------------------------------------------------------------------------------------//
    //---------------------------------------------------------Método Auxiliar-----------------------------------------------------------//
    //-----------------------------------------------------------------------------------------------------------------------------------//
	
	public static String dateToString(String _dataAten) 
	{
		String[] data = _dataAten.split("-");
		return data[2] + "/" + data[1] + "/" + data[0];
	}
}
