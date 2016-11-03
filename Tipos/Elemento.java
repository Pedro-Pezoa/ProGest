package Tipos;

public class Elemento<Tipo> 
{
    protected Tipo info;
    protected Elemento<Tipo> prox, ant, dir, esq; // Se for árvore, ira ser prox = dir e ant = esq
    
    //-----------------------------------------------------Construtor-------------------------------------------------//
    
    public Elemento(Tipo _conteudo)
    {
        this.info = _conteudo;
    }
    
    //--------------------------------------------------Getters e Setters----------------------------------------------------//
    
    public void setInfo(Tipo _c)
    {
        this.info = _c;
    }
    
    public Tipo getInfo()
    {
       return this.info;
    }
    
    public void setAnt(Elemento<Tipo> _c)
    {
        this.ant = _c;
    }
    
    public Elemento<Tipo> getAnt()
    {
       return this.ant;
    }
    
    public void setProx(Elemento<Tipo> _c)
    {
        this.prox = _c;
    }
    
    public Elemento<Tipo> getProx()
    {
       return this.prox;
    }
    
    public void setDir(Elemento<Tipo> _c)
    {
        this.dir = _c;
    }
    
    public Elemento<Tipo> getDir()
    {
       return this.dir;
    }
    
    public void setEsq(Elemento<Tipo> _c)
    {
        this.esq = _c;
    }
    
    public Elemento<Tipo> getEsq()
    {
       return this.esq;
    }
    
    //------------------------------------------------Métodos Apocalipticos------------------------------------------------------//
    
    public String toString()
    {
    	return this.info + "";
    }
    
    public int hashCode()
    {
    	int ret = super.hashCode();
    	ret *= 7 + this.info.hashCode();
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
    		Elemento<Tipo> proxN = (Elemento<Tipo>)_obj;
    		
    		if (this.info == proxN.info)
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
    
    public Elemento<Tipo> clone()
    {
    	return new Elemento<Tipo>(this);
    }
    
    public Elemento(Elemento<Tipo> _elem)
    {
    	this.ant = _elem.ant;
    	this.dir = _elem.dir;
    	this.esq = _elem.esq;
    	this.prox = _elem.prox;
    	this.info = _elem.info;
    }
}
