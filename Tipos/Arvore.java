package Tipos;

import Classes.CharOcorrencia;

public class Arvore<Tipo extends Comparable<Tipo>> 
{
    protected Elemento<Tipo> raizPrincipal;
    
    public Arvore() 
    {
		this(null);
	}
    
    public Arvore(Elemento<Tipo> N) 
    {
		this.raizPrincipal = N;
	}
    
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
    
    public int qtosNos()
    {
        return qtosNos(raizPrincipal);
    }
    
    public Elemento<Tipo> getRaiz()
    {
    	return this.raizPrincipal;
    }
    
    private int qtosNos(Elemento<Tipo> Raiz)
    {
        if (Raiz == null) return 0;
        return (qtosNos(Raiz.getDir()) + qtosNos(Raiz.getEsq()) + 1);
    }
    
    private String visita(Elemento<Tipo> Raiz)
    {
        if (Raiz == null) return "";
        return visita(Raiz.getEsq()) + " " + Raiz.getInfo()+ " " + visita (Raiz.getDir());
    }
    
    public String getQtosEsqDir(Tipo _tipo)
    {
    	return this.getElemento(this.raizPrincipal, _tipo, "");
    }
    
    private String getElemento(Elemento<Tipo> _raiz, Tipo _tipo, String _result) 
    {
    	if (this.existe(_raiz.getDir(), _tipo)) return getElemento(_raiz.getDir(), _tipo, _result+"D");
    	if (this.existe(_raiz.getEsq(), _tipo)) return getElemento(_raiz.getEsq(), _tipo, _result+"E");
    	return _result;
    }
    
    private boolean existe(Elemento<Tipo> _raiz, Tipo _tipo) 
    {
    	if (_raiz == null) return false;
		return _raiz.getInfo().equals(_tipo) || this.existe(_raiz.getDir(), _tipo) || this.existe(_raiz.getEsq(), _tipo);
	}

	public String toString()
    {
        return "[ "+ visita(raizPrincipal) + " ]";
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
}
