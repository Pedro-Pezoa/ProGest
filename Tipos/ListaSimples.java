package Tipos;

public class ListaSimples<Tipo extends Comparable<Tipo>> 
{
	protected Elemento<Tipo> inicio, fim, atual;
    protected boolean estaPercorrendo;
    
    //---------------------------------------------------------------------------------------------------------------------------//
    //---------------------------------------------------------Construtor--------------------------------------------------------//
    //---------------------------------------------------------------------------------------------------------------------------//
    
    public ListaSimples()
    {
        this.inicio = this.fim = null;
    }
    
    //---------------------------------------------------------------------------------------------------------------------------//
    //----------------------------------------------------------Getters----------------------------------------------------------//
    //---------------------------------------------------------------------------------------------------------------------------//
    
    public Elemento<Tipo> getInicio()
    {
    	return this.inicio;
    }
    
    public Elemento<Tipo> getFim()
    {
    	return this.fim;
    }
    
    public Elemento<Tipo> getAtual()
    {
    	return this.atual;
    }
    
    public boolean estaPercorrendo()
    {
    	return this.estaPercorrendo;
    }
    
    //-------------------------------------------------------------------------------------------------------------------------//
    //---------------------------------------------------Métodos Principais----------------------------------------------------//
    //-------------------------------------------------------------------------------------------------------------------------//
    
    public void incluirOrdenado(Tipo N)
    {
    	Elemento<Tipo> Aux = this.inicio, Ant = null;
        
        while (Aux!=null && Aux.getInfo().compareTo(N) < 0)
        {
            Ant = Aux;
            Aux = Aux.getProx();
        }       
        
        if (Ant == null) incluirNoInicio(N);
        else if (Aux == null) incluirNoFim(N);
        else 
        {
        	Elemento<Tipo> Novo = new Elemento<Tipo>(N);
            Novo.setProx(Aux);
            Ant.setProx(Novo);
        }
    }
    
    public void excluirOrdenado(Tipo N)
    {
    	Elemento<Tipo> Aux = this.inicio, Ant = null;
        
        while (Aux != null && Aux.getInfo().compareTo(N) < 0)
        {
            Ant = Aux;
            Aux = Aux.getProx();
        }
        
        if (Aux != null && Aux.getInfo().equals(N))
        { 
            if (Ant == null) excluirDoInicio();
            else if (Aux == fim) excluirDoFim();
            else Ant.setProx(Aux.getProx());
        }
    }
    
    public void incluirNoInicio(Tipo N)
    {
    	Elemento<Tipo> Novo = new Elemento<Tipo>(N);
        Novo.setProx(inicio);
        
        if (inicio == null) fim = Novo;
        inicio = Novo;
    }
    
    public void excluirDoInicio()
    {
        if (this.inicio == this.fim) this.fim = this.inicio = null;
        if (this.inicio != null) this.inicio = this.inicio.getProx();
    }
    
    public void incluirNoFim(Tipo N)
    {
    	Elemento<Tipo> Novo = new Elemento<Tipo>(N);
    	
        if (this.fim != null) this.fim.setProx(Novo);
        this.fim = Novo;
        if (inicio == null) inicio = Novo;
    }
    
    public void excluirDoFim()
    {
        if (this.inicio != null)
        {
        	Elemento<Tipo> Aux = this.inicio;
        	
            while (Aux != null && Aux.getProx() != fim)
               Aux = Aux.getProx();
            
            if (Aux != null) Aux.setProx(null);
            
            if (this.inicio == this.fim)
                this.inicio = this.fim = null;
            
            else this.fim = Aux;
        }
    }
    
    public void excluir(Tipo N)
    {  
    	Elemento<Tipo> Ant = null, Aux = this.inicio;

        while (Aux != null)
        { 
            if (Aux.getInfo().equals(N))
            {
                if (this.inicio == this.fim) this.fim = this.inicio = null;
                
                else
                {  
                    if (Aux == this.fim) this.fim = Ant; 

                    if (Ant != null) Ant.setProx(Aux.getProx());
                    else this.inicio = this.inicio.getProx();
               }
            } 
            else Ant = Aux;
            
            Aux = Aux.getProx();
        }
    }
    
    //---------------------------------------------------------------------------------------------------------------------------//
    //---------------------------------------------------Métodos Auxiliares------------------------------------------------------//
    //---------------------------------------------------------------------------------------------------------------------------//
    
    public Elemento<Tipo> ondeEsta(Tipo N)
    {
        Elemento<Tipo> Aux = this.inicio; 
        
        while (Aux != null && Aux.getInfo().compareTo(N) < 0)
            Aux = Aux.getProx();
          
        if (Aux != null && Aux.getInfo() == N) return Aux;
        return null;
    }
    
    public ListaSimples<Tipo> clonaLista() throws Exception 
	{
    	ListaSimples<Tipo> listaNova = new ListaSimples<Tipo>();
    	Elemento<Tipo> Aux = this.inicio;
    	
		while (Aux != null)
		{
			listaNova.incluirNoFim(Aux.getInfo());
			Aux = Aux.getProx();
		}
		return listaNova;
	}
    
    public void limparLista()
	{
		while (this.inicio != null) this.excluirDoFim();
	}
    
    public void juntarLista(ListaSimples<Tipo> _lista) throws Exception
	{
    	Elemento<Tipo> Aux = _lista.inicio;
    	
		while (Aux != null)
		{
			this.incluirNoFim(Aux.getInfo());
			Aux = Aux.getProx();
		}
	}
    
    //----------------------------------------------------------------------------------------------------------------------------//
    //--------------------------------------------------Métodos de Percursso------------------------------------------------------//
    //----------------------------------------------------------------------------------------------------------------------------//
    
    public void iniciaPercurssoSequencial()
    {
    	this.atual = this.inicio;
    	this.estaPercorrendo = true;
    }
    
    public boolean podePercorrer()
    {
    	return this.atual != null;
    }
    
    public void setPosAtual()
    {
    	this.atual = this.atual.getProx();
    }
    
    //---------------------------------------------------------------------------------------------------------------------------//
    //-------------------------------------------------Métodos Apocalipticos-----------------------------------------------------//
    //---------------------------------------------------------------------------------------------------------------------------//
    
    public String toString()
    {
        String ret = "[" + this.inicio;
        Elemento<Tipo> aux = this.inicio.prox;
        
        while (aux != null)
        {
            ret += ("," + aux);
            aux = aux.getProx();
        }
        return ret + "]";        
    }
    
    public int hashCode()
    {
    	int ret = super.hashCode();
    	ret *= 7 + this.inicio.hashCode();
    	ret *= 7 + new Boolean(this.estaPercorrendo).hashCode();
    	return ret;
    }
}
