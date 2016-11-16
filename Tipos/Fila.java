package Tipos;

public class Fila <Tipo> 
{
    protected int inicio, fim, size;
    protected Elemento<Tipo>[] fila;
    
    //---------------------------------------------------------------------------------------------------------------------------//
    //-------------------------------------------------------Construtores--------------------------------------------------------//
    //---------------------------------------------------------------------------------------------------------------------------//
    
    public Fila() throws Exception {this (100);}
    
    @SuppressWarnings("unchecked")
	public Fila(int _tam) throws Exception
    {
    	if (_tam <= 0)
    		throw new Exception("Tamanho Inválido");
    	this.inicio = this.fim = -1;
        this.fila = new Elemento[_tam];
        this.size = 0;
    }
   
    //---------------------------------------------------------------------------------------------------------------------------//
    //-----------------------------------------------------Métodos Principais----------------------------------------------------//
    //---------------------------------------------------------------------------------------------------------------------------//
    
    public void enfileirar(Elemento<Tipo> _elem) throws Exception
    {
    	if ((_elem == null) || (((Object)_elem instanceof String) && (_elem.equals("")))) 
    		throw new Exception("Elemento Inválido");
    	
        if (isFull())
        	throw new Exception("Fila Cheia");
        
        if (isEmpty()) inicio = 0;
        fim = (fim + 1) % this.fila.length;
        this.fila[fim] = _elem;
        this.size++;
    }
    
    public Elemento<Tipo> desefileirar() throws Exception
    {
        if (isEmpty()) 
        	throw new Exception("Fila Vazia");
        
        int indElemento = inicio;
        inicio = (inicio + 1) % fila.length;
        
        if (inicio == ((fim + 1) % fila.length))
        	inicio = fim = -1;
        this.size--;
        
        return this.fila[indElemento];
    }
    
    public Elemento<Tipo> consultaPrimeiro() throws Exception
    {
        if (isEmpty()) 
        	throw new Exception("Fila Vazia");
        return this.fila[inicio];        
    }    
    
    public boolean isFull()
    {
        return (((inicio == 0) && ((fim + 1) == this.fila.length)) || (fim + 1 == inicio));
    }
    
    public boolean isEmpty()
    {
        return (inicio == -1);
    }
    
    public int getSize() throws Exception
    {
        return this.size;
    }
    
    //---------------------------------------------------------------------------------------------------------------------------//
    //---------------------------------------------------Métodos Adicionais------------------------------------------------------//
    //---------------------------------------------------------------------------------------------------------------------------//
   
    public Fila<Tipo> clonarFila() throws Exception
    {
        Fila<Tipo> novaFila =  new Fila<Tipo>(this.fila.length);
        int i = inicio;
        boolean acabou = this.isEmpty();
        
        while (!acabou) 
        {
        	novaFila.enfileirar(this.fila[i]);
            i = (i + 1) % this.fila.length; // garante a circularidade
            
            if ((inicio == 0) && (fim < this.fila.length-1))
         	   acabou = (i > fim);
            else if (fim == this.fila.length-1) 
         	   acabou = (i == 0);
            else if (inicio > fim) // esta em wrap
         	   acabou = ((i <= inicio) && (i > fim));
            else // inicio == Fim
         	   acabou = (i == inicio) || (i == inicio + 1); 
        }
        return novaFila;
    }
    
    public void limparFila()
	{
    	int i = inicio;
        boolean acabou = this.isEmpty();
        
        while (!acabou) 
        {
        	this.fila[i] = null;
            i = (i + 1) % this.fila.length; // garante a circularidade
            
            if ((inicio == 0) && (fim < this.fila.length-1))
         	   acabou = (i > fim);
            else if (fim == this.fila.length-1) 
         	   acabou = (i == 0);
            else if (inicio > fim) // esta em wrap
         	   acabou = ((i <= inicio) && (i > fim));
            else // inicio == Fim
         	   acabou = (i == inicio) || (i == inicio + 1); 
        }
        this.size = 0;
    	inicio = fim = -1;
	}
    
    public void inverteFila()throws Exception
	{
    	if (this.isEmpty())
    		throw new Exception("Fila Vazia");
    	
    	Elemento<Tipo> aux = null;
		int lento = inicio, rapido = 0;
		if (this.inicio != this.fila.length)
			rapido = inicio + 1;
		
		while (lento == this.fim) 
		{
			aux = this.fila[lento];
			this.fila[lento] = this.fila[rapido];
			this.fila[rapido] = aux;
			lento++;
			rapido++;
		}
	}
    
    public void juntarPilha(Fila<Tipo> _fila) throws Exception
	{
    	if (_fila == null)
    		throw new Exception("Fila Inválido");
    	
		this.aumentaFila(_fila.getSize());
		int i = inicio;
        boolean acabou = this.isEmpty();
        
        while (!acabou) 
        {
        	this.fila[i] = null;
            i = (i + 1) % this.fila.length; // garante a circularidade
            
            if ((inicio == 0) && (fim < this.fila.length-1))
         	   acabou = (i > fim);
            else if (fim == this.fila.length-1) 
         	   acabou = (i == 0);
            else if (inicio > fim) // esta em wrap
         	   acabou = ((i <= inicio) && (i > fim));
            else // inicio == Fim
         	   acabou = (i == inicio) || (i == inicio + 1); 
        }
	}
    
    @SuppressWarnings("unchecked")
	public void aumentaFila(int _tam) throws Exception
	{
    	if (_tam <= 0)
    		throw new Exception("Tamanho Inválido");
    	
		Elemento<Tipo>[] filaVelha = this.fila;
		this.fila = new Elemento[(_tam + this.fila.length)];
		
		for (int i = 0; i < filaVelha.length; i++) 
			this.fila[i] = filaVelha[i];
	}
    
    //---------------------------------------------------------------------------------------------------------------------------//
    //---------------------------------------------------Métodos Apocalipticos---------------------------------------------------//
    //---------------------------------------------------------------------------------------------------------------------------//
    
    public String toString()
    {
       if (this.isEmpty()) return "[]";
       
       String txt = "[";
       int i = inicio;
       boolean acabou = this.isEmpty();
       
       while (!acabou) 
       {
    	   txt += this.fila[i] + ",";
           i = (i + 1) % this.fila.length; // garante a circularidade
           
           if ((inicio == 0) && (fim < this.fila.length-1))
        	   acabou = (i > fim);
           else if (fim == this.fila.length-1) 
        	   acabou = (i == 0);
           else if (inicio > fim) // esta em wrap
        	   acabou = ((i <= inicio) && (i > fim));
           else // inicio == Fim
        	   acabou = (i == inicio) || (i == inicio + 1); 
       }
       return txt + "]";
    }
            
    public int hashCode()
    {
    	int ret = super.hashCode();
    	
    	ret = ret*7 + new Integer(this.inicio).hashCode();
    	ret = ret*7 + new Integer(this.fim).hashCode();
    	
    	for (int i = inicio; i <= this.fim-1;)
        {
     	   ret = ret*7 + this.fila[i].hashCode();
           i = (i + 1) % this.fila.length; // garante a rotatividade
        }
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
    		Fila<Tipo> proxN = (Fila<Tipo>)_obj;
    		
    		if ((this.inicio == proxN.inicio) && (this.fim == proxN.fim))
    		{
    			int i = inicio;
    	        boolean acabou = this.isEmpty();
    	        
    	        while (!acabou) 
    	        {
    	        	if (!this.fila[i].equals(proxN.fila[i]))
						return false;
    	            i = (i + 1) % this.fila.length; // garante a circularidade
    	            
    	            if ((inicio == 0) && (fim < this.fila.length-1))
    	         	   acabou = (i > fim);
    	            else if (fim == this.fila.length-1) 
    	         	   acabou = (i == 0);
    	            else if (inicio > fim) // esta em wrap
    	         	   acabou = ((i <= inicio) && (i > fim));
    	            else // inicio == Fim
    	         	   acabou = (i == inicio) || (i == inicio + 1); 
    	        }
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
