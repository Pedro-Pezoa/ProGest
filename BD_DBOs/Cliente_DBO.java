package BD_DBOs;

public class Cliente_DBO implements Cloneable, Comparable<Cliente_DBO>
{
	protected int codClien;
	protected String nomeClien, teleClien, emailClien;
	
	//---------------------------------------------------------------------------------------------------------------------------//
    //---------------------------------------------------------Construtor--------------------------------------------------------//
    //---------------------------------------------------------------------------------------------------------------------------//
	
	public Cliente_DBO(int _novoCod, String _novoNome, String _novoEmail, String _novoTele) throws Exception
	{
		this.setCodClien(_novoCod);
		this.setNomeClien(_novoNome);
		this.setEmailClien(_novoEmail);
		this.setTeleClien(_novoTele);
	}

	//---------------------------------------------------------------------------------------------------------------------------//
    //-----------------------------------------------------Getters e Setters-----------------------------------------------------//
    //---------------------------------------------------------------------------------------------------------------------------//
	
	public int getCodClien() 
	{
		return codClien;
	}

	public void setCodClien(int _codClien) throws Exception
	{
		if (_codClien <= 0) throw new Exception("Código Inválido");
		this.codClien = _codClien;
	}

	public String getNomeClien() 
	{
		return nomeClien;
	}

	public void setNomeClien(String _nomeClien) throws Exception
	{
		if (_nomeClien == null || _nomeClien.equals("")) throw new Exception("Nome do Cliente Inválido");
		this.nomeClien = _nomeClien;
	}

	public String getTeleClien() 
	{
		return this.teleClien;
	}

	public void setTeleClien(String _teleClien) throws Exception
	{
		if (_teleClien == null || _teleClien.equals("")) throw new Exception("Telefone do Cliente Inválido");
		this.teleClien = _teleClien;
	}

	public String getEmailClien() 
	{
		return this.emailClien;
	}

	public void setEmailClien(String _emailClien) throws Exception
	{
		if (_emailClien == null || _emailClien.equals("")) throw new Exception("Email do Cliente Inválido");
		this.emailClien = _emailClien;
	}
	
	//------------------------------------------------------------------------------------------------------------------------------//
    //-----------------------------------------------------Métodos Apocalipticos----------------------------------------------------//
    //------------------------------------------------------------------------------------------------------------------------------//
	
	@Override
	public String toString() 
	{
		return "Código do Cliente: " + this.codClien + "--Nome do Cliente: " + this.nomeClien + 
				"--Email do Cliente=" + this.emailClien + "--Telefone do Cliente: " + this.teleClien;
	}
	
	@Override
	public int hashCode() 
	{
		int result = super.hashCode();
		result *= 7 + new Integer(this.codClien).hashCode();
		result *= 7 + this.emailClien.hashCode();
		result *= 7 + this.nomeClien.hashCode();
		result *= 7 + this.teleClien.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object _obj) 
	{
		if (this == _obj)return true;
		if (_obj == null)return false;
		
		if (this.getClass() == _obj.getClass())
		{
			Cliente_DBO outro = (Cliente_DBO) _obj;
			if (this.codClien == outro.codClien && this.nomeClien.equals(outro.nomeClien) &&
				this.emailClien.equals(outro.emailClien) &&this.teleClien.equals(outro.teleClien))
				return true;
		}
		
		if (_obj instanceof String)
		{
			String outro = (String) _obj;
			if (this.toString().equals(outro)) return true;
		}
		return false;
	}
	
	public Cliente_DBO clone()
	{
		return new Cliente_DBO(this);
	}
	
	public Cliente_DBO(Cliente_DBO _outro) 
	{
		this.codClien = _outro.codClien;
		this.nomeClien = _outro.nomeClien;
		this.emailClien = _outro.emailClien;
		this.teleClien = _outro.teleClien;
	}

	@Override
	public int compareTo(Cliente_DBO _outro) 
	{
		if (this.codClien > _outro.codClien) return 1;
		if (this.codClien < _outro.codClien) return -1;
		return 0;
	}
}
