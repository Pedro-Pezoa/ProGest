package BD_DBOs;

public class Atendimento_DBO implements Cloneable, Comparable<Atendimento_DBO>
{
	protected int codAten, codClien;
	protected String nomeAten, dataAten, tipoeAten, obsClien, statusAten;
	
	//---------------------------------------------------------------------------------------------------------------------------//
    //---------------------------------------------------------Construtor--------------------------------------------------------//
    //---------------------------------------------------------------------------------------------------------------------------//
	
	public Atendimento_DBO(int _novoAten, int _novoCod, String _novoNome, String _novaData, String _novoTipo, String _novaObsClien, String _novoStatus) throws Exception
	{
		this.setCodAten(_novoAten);
		this.setCodClien(_novoCod);
		this.setNomeAten(_novoNome);
		this.setDataAten(_novaData);
		this.setTipoeAten(_novoTipo);
		this.setObsClien(_novaObsClien);
		this.setStatusAten(_novoStatus);
	}

	//---------------------------------------------------------------------------------------------------------------------------//
    //-----------------------------------------------------Getters e Setters-----------------------------------------------------//
    //---------------------------------------------------------------------------------------------------------------------------//
	
	public int getCodAten() 
	{
		return codAten;
	}

	public void setCodAten(int _codAten) throws Exception
	{
		if (_codAten <= 0) throw new Exception("Código Atendente Inválido");
		this.codAten = _codAten;
	}

	public int getCodClien() 
	{
		return codClien;
	}

	public void setCodClien(int _codClien) throws Exception
	{
		if (_codClien <= 0) throw new Exception("Código Cliente Inválido");
		this.codClien = _codClien;
	}

	public String getNomeAten() 
	{
		return nomeAten;
	}

	public void setNomeAten(String _nomeAten) throws Exception
	{
		if (_nomeAten == null || _nomeAten.equals("")) throw new Exception("Nome do Atendente Inválido");
		this.nomeAten = _nomeAten;
	}

	public String getDataAten() 
	{
		return dataAten;
	}

	public void setDataAten(String _dataAten) throws Exception
	{
		if (_dataAten == null || _dataAten.equals("")) throw new Exception("Data do Atendimento Inválido");
		this.dataAten = _dataAten;
	}

	public String getTipoeAten() 
	{
		return tipoeAten;
	}

	public void setTipoeAten(String _tipoeAten) throws Exception
	{
		if (_tipoeAten == null || _tipoeAten.equals("")) throw new Exception("Tipo do Atendimento Inválido");
		this.tipoeAten = _tipoeAten;
	}

	public String getObsClien() 
	{
		return obsClien;
	}

	public void setObsClien(String _obsClien) throws Exception
	{
		if (_obsClien == null || _obsClien.equals("")) throw new Exception("Observação 1 Inválido");
		this.obsClien = _obsClien;
	}

	public String getStatusAten() 
	{
		return this.statusAten;
	}
	
	public void setStatusAten(String _novoStatus) 
	{
		this.statusAten = _novoStatus;
	}
	
	//------------------------------------------------------------------------------------------------------------------------------//
    //-----------------------------------------------------Métodos Apocalipticos----------------------------------------------------//
    //------------------------------------------------------------------------------------------------------------------------------//
	
	@Override
	public int hashCode() 
	{
		int result = super.hashCode();
		result *= 7 + this.codAten;
		result *= 7 + this.codClien;
		result *= 7 + this.dataAten.hashCode();
		result *= 7 + this.nomeAten.hashCode();
		result *= 7 + this.obsClien.hashCode();
		result *= 7 + this.statusAten.hashCode();
		result *= 7 + this.tipoeAten.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object _obj) 
	{
		if (this == _obj)return true;
		if (_obj == null)return false;
		
		if (getClass() == _obj.getClass())
		{
			Atendimento_DBO outro = (Atendimento_DBO) _obj;
			if (this.codAten == outro.codAten && this.codClien == outro.codClien && this.nomeAten.equals(outro.nomeAten) && this.dataAten.equals(outro.dataAten) &&
				this.tipoeAten.equals(outro.tipoeAten) && this.obsClien.equals(outro.obsClien) && this.statusAten.equals(outro.statusAten))
				return true;
		}
		
		if (_obj instanceof String)
		{
			String outro = (String) _obj;
			if (this.toString().equals(outro))return true;
		}
		return false;
	}

	public Atendimento_DBO clone()
	{
		return new Atendimento_DBO(this);
	}
	
	public Atendimento_DBO(Atendimento_DBO _outro) 
	{
		this.codClien = _outro.codClien;
		this.codAten = _outro.codAten;
		this.nomeAten = _outro.nomeAten;
		this.dataAten = _outro.dataAten;
		this.codClien = _outro.codClien;
		this.tipoeAten = _outro.tipoeAten;
		this.tipoeAten = _outro.tipoeAten;
		this.obsClien = _outro.obsClien;
		this.statusAten = _outro.statusAten;
	}

	@Override
	public int compareTo(Atendimento_DBO _outro) 
	{
		if (this.codAten > _outro.codAten) return 1;
		if (this.codAten < _outro.codAten) return -1;
		return 0;
	}
}
