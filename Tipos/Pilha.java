package Tipos;

public class Pilha<Tipo> implements Cloneable
{
    protected int topo;
    protected Elemento<Tipo>[] pilha;
    
    //-------------------------------------------------------------------------------------------------------------------------//
    //------------------------------------------------------Construtores-------------------------------------------------------//
    //-------------------------------------------------------------------------------------------------------------------------//
    
    public Pilha() throws Exception{this (100);}
    
    @SuppressWarnings("unchecked")
	public Pilha(int _tam) throws Exception
    {
    	if (_tam <= 0)
    		throw new Exception("Tamanho Inválido");
        topo = 0;
        pilha = new Elemento[_tam];
    }

    //---------------------------------------------------------------------------------------------------------------------------//
	//---------------------------------------------------Métodos Principais------------------------------------------------------//
    //---------------------------------------------------------------------------------------------------------------------------//
    
    public void empilhar(Elemento<Tipo> _elem) throws Exception
    {
    	if ((_elem == null) || (((Object)_elem instanceof String) && (_elem.equals("")))) 
    		throw new Exception("Elemento Inválido");
        if (isFull())
        	throw new Exception("Pilha Cheia");
        pilha[topo++] = _elem;
    }
    
    public Elemento<Tipo> desempilhar() throws Exception
    {
        if (isEmpty())
        	throw new Exception("Pilha Vazia");
        topo--;
        return pilha[topo];
    }
    
    public Elemento<Tipo> consultaTopo() throws Exception
    {
        if (isEmpty())
        	throw new Exception("Pilha Vazia");
        return pilha[topo-1];        
    }    
    
    public boolean isFull()
    {
        return (topo == pilha.length);
    }
    
    public boolean isEmpty()
    {
        return (topo == 0);
    }
    
    public int getSize()
    {
        return this.topo;
    }
    
    //---------------------------------------------------------------------------------------------------------------------------//
    //----------------------------------------------------Métodos Adicionais-----------------------------------------------------//
    //---------------------------------------------------------------------------------------------------------------------------//
    
    public Pilha<Tipo> clonaPilha() throws Exception 
	{
    	Pilha<Tipo> pilhaNova = new Pilha<Tipo>(this.topo);
		for (int i = 0; i < this.topo; i++) 
			pilhaNova.empilhar(this.pilha[i]);
		return pilhaNova;
	}
    
    public void limparPilha()
	{
		for (int i = 0; i < pilha.length; i++) 
			this.pilha[i] = null;
		topo = 0;
	}
    
    public void invertePilha()
	{
		int lento = 0, rapido = topo-1;
		Elemento<Tipo> aux = null;
		
		while (lento < rapido) 
		{
			aux = this.pilha[lento];
			this.pilha[lento] = this.pilha[rapido];
			this.pilha[rapido] = aux;
			lento++;
			rapido--;
		}
	}
    
    public void juntarPilha(Pilha<Tipo> _pilha) throws Exception
	{
    	if (_pilha == null)
    		throw new Exception("Pilha Inválido");
    	
		this.aumentaPilha(_pilha.topo);
		for (int i = this.topo; i < this.pilha.length; i++) 
			this.empilhar(_pilha.desempilhar());
	}
    
    @SuppressWarnings("unchecked")
	public void aumentaPilha(int _tam) throws Exception
	{
    	if (_tam <= 0)
    		throw new Exception("Tamanho Inválido");
    	
		Elemento<Tipo>[] pilhaVelha = this.pilha;
		this.pilha = new Elemento[(_tam + this.topo)];
		
		for (int i = 0; i < pilhaVelha.length; i++) 
			this.pilha[i] = pilhaVelha[i];
	}
            
    //---------------------------------------------------------------------------------------------------------------------------//
    //---------------------------------------------------Métodos Apocalipticos---------------------------------------------------//
    //---------------------------------------------------------------------------------------------------------------------------//
    
    public String toString()
    {
    	if (this.isEmpty()) return "[]";
    	
    	String txt = "[ ";
    	for (int i = topo-1; i > 0; i--)  
    		txt += this.pilha[i].toString() + " , ";
    	return txt + this.pilha[0].toString() + " ]";
    }
    
    public int hashCode()
    {
    	int ret = super.hashCode();
    	
    	ret = ret*7 + new Integer(this.topo).hashCode();
    	
    	for (int i = topo-1; i > -1; i--)
    		ret = ret*7 + this.pilha[i].hashCode();
    	return ret;
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public boolean equals(Object _obj) 
    {
    	if (_obj == null)
    		return false;
    	
    	if (this == _obj)
    		return true;
    	
    	if (this.getClass() == _obj.getClass())
    	{
    		Pilha<Tipo> proxN = (Pilha<Tipo>)_obj;
    		
    		if (this.topo == proxN.topo)
    		{
    			for (int i = topo-1; i > -1; i--) 
					if (!this.pilha[i].equals(proxN.pilha[i]))
						return false;
    		}
    		return true;
    	}
    	
    	if (_obj instanceof String)
    	{
    		String proxN = (String)_obj;
    		
    		if (this.toString().equals(proxN.toString()))
    			return true;
    	}
    	return false;
    }
}
