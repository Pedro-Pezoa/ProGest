package Tipos;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Arvore<Tipo extends Comparable<Tipo>> implements Serializable 
{
    protected Elemento<Tipo> raizPrincipal;
    
    //------------------------------------------------------------------------------------------------------------------//
    //-----------------------------------------------------Construtores-------------------------------------------------//
    //------------------------------------------------------------------------------------------------------------------//
    
    public Arvore() 
    {
		this(null);
	}
    
    public Arvore(Elemento<Tipo> N) 
    {
		this.raizPrincipal = N;
	}
    
    //-----------------------------------------------------------------------------------------------------------------------//
    //--------------------------------------------------Getters e Setters----------------------------------------------------//
    //-----------------------------------------------------------------------------------------------------------------------//
    
    public Elemento<Tipo> getRaiz()
    {
    	return this.raizPrincipal;
    }
    
    public int getQtosNos()
    {
        return qtosNos(raizPrincipal);
    }
    
    private int qtosNos(Elemento<Tipo> Raiz)
    {
        if (Raiz == null) return 0;
        return (qtosNos(Raiz.getDir()) + qtosNos(Raiz.getEsq()) + 1);
    }
    
    public String getQtosEsqDir(Tipo _folha)
    {
    	return this.getElemento(this.raizPrincipal, _folha, "");
    }
    
    private String getElemento(Elemento<Tipo> _raiz, Tipo _folha, String _result) 
    {
    	if (this.existe(_raiz.getDir(), _folha)) return getElemento(_raiz.getDir(), _folha, _result+"D");
    	if (this.existe(_raiz.getEsq(), _folha)) return getElemento(_raiz.getEsq(), _folha, _result+"E");
    	return _result;
    }
    
    //---------------------------------------------------------------------------------------------------------------------------//
    //-----------------------------------------------------Métodos Principais----------------------------------------------------//
    //---------------------------------------------------------------------------------------------------------------------------//
    
    public void incluir(Elemento<Tipo> N)
    {
        incluir(raizPrincipal, N);
    }
    
    public void incluirNaEsquerda(Elemento<Tipo> N)
    {
        incluirNaEsquerda(raizPrincipal, N);
    }
    
    public void incluirNaDireita(Elemento<Tipo> N)
    {
    	incluirNaDireita(raizPrincipal, N);
    }
    
    private void incluirNaEsquerda(Elemento<Tipo> Raiz, Elemento<Tipo> n) 
    {
    	if (Raiz == null) // arvore vazia
            raizPrincipal = n;
        else
        {
            if (Raiz.getEsq() == null) 
                Raiz.setEsq(n);
            else
            	incluirNaEsquerda(Raiz.getEsq(), n);
        }
	}
    
    private void incluirNaDireita(Elemento<Tipo> Raiz, Elemento<Tipo> n) 
    {
    	if (Raiz == null) // arvore vazia
            raizPrincipal = n;
        else
        {
            if (Raiz.getDir() == null) 
                Raiz.setDir(n);
            else
            	incluirNaDireita(Raiz.getEsq(), n);
        }
	}

	private void incluir(Elemento<Tipo> Raiz, Elemento<Tipo> N)
    {
        if (Raiz == null) // arvore vazia
            raizPrincipal = N;
        else
        {
	        if (Raiz.getInfo().compareTo(N.getInfo()) > 0) // incluir na Esquerda
	        {
	            if (Raiz.getEsq() == null) 
	                Raiz.setEsq(N);
	            else
	                incluir(Raiz.getEsq(), N);
	        }
	        else // <= N , incluir na Direita
	        {
	            if (Raiz.getDir() == null)
	                Raiz.setDir(N);
	            else          
	                incluir(Raiz.getDir(), N);
	        }
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------//
    //---------------------------------------------------Métodos Auxiliares------------------------------------------------------//
    //---------------------------------------------------------------------------------------------------------------------------//
	
	private boolean existe(Elemento<Tipo> _raiz, Tipo _folha) 
    {
    	if (_raiz == null) return false;
		return _raiz.getInfo().equals(_folha) || this.existe(_raiz.getDir(), _folha) || this.existe(_raiz.getEsq(), _folha);
	}
	
	public Tipo existeEsqDir(String _aux) 
	{
		return existeEsqDir(this.raizPrincipal, _aux , 0);
	}

	private Tipo existeEsqDir(Elemento<Tipo> _elem, String _aux, int _i)
	{
		if (_i == _aux.length())
		{
			if (_elem.getDir() == null && _elem.getEsq() == null) return _elem.getInfo();
			return null;
		}
		if (_aux.charAt(_i) == 'D') return existeEsqDir(_elem.getDir(), _aux, ++_i);
		if (_aux.charAt(_i) == 'E') return existeEsqDir(_elem.getEsq(), _aux, ++_i);
		return null;
	} 
	
    //---------------------------------------------------------------------------------------------------------------------------//
    //---------------------------------------------------Métodos Apocalipticos---------------------------------------------------//
    //---------------------------------------------------------------------------------------------------------------------------//
	
	public int hashCode()
	{
		int ret = super.hashCode();
		ret *= 7 + this.raizPrincipal.hashCode();
		return ret;
	}
	
	public String toString()
    {
        return "[ "+ visita(raizPrincipal) + " ]";
    }
	
	private String visita(Elemento<Tipo> Raiz)
    {
        if (Raiz == null) return "";
        return visita(Raiz.getEsq()) + " " + Raiz.getInfo()+ " " + visita (Raiz.getDir());
    }
}
