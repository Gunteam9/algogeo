package algo;

public class Delaunay {
	private int[] x;
	private int[] y;
	
	public Delaunay() {
	
	}

	public Delaunay(int[] x, int[] y) {
		super();
		this.x = x;
		this.y = y;
	}
	
    //Triangulation de Delaunay
    
    public int tropPres(int i, int[] x, int[] y) {     	
    	for (int k = 0; k < i; k++) {
    		if (Math.abs(x[k]-x[i]) < 10 && Math.abs(y[k]-y[i]) < 10) 
    			return 1;    		
    	}
    	
    	return 0;
     } 
    
    public int plusProcheVoisin (int i) {
    	int jmin = 0; 
    	long dist2, distmin = Long.MAX_VALUE;
    	int n = x.length;
    	
    	for (int j = 0; j < n; j++) 
    		if (i != j) {
    			dist2 = (x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]);
    			if (dist2 < distmin) {
    				distmin=dist2; 
    				jmin=j; 
    			}
    		}
    	
    	return jmin;
    }
    
    public int voisinSuivantDroite(int i,int j) { 
    	int k; 
    	int v1x, v1y, v2x, v2y, numerovoisin = -1;
    	double prodscal, longki2, longki, longkj2, longkj, coskij, cosmin, det;
    	cosmin=1;
    	
    	for (k = 0; k < x.length; k++) 
    		if (k != i && k != j) { 
    			v1x = x[j] - x[i];
    			v1y = y[j]-y[i];
    			v2x = x[k] - x[i];
    			v2y = y[k]-y[i]; 
    			det = v1x * v2y - v1y * v2x;
    			
    			if (det < 0) /* on cherche un point à droite */ { 
    				prodscal = (x[i] - x[k]) * (x[j] - x[k]) + (y[i] - y[k]) * (y[j] - y[k]);
    				longki2 = (x[i] - x[k]) * (x[i] - x[k]) + (y[i] - y[k]) * (y[i] - y[k]);
    				longkj2 = (x[j] - x[k]) * (x[j] - x[k]) + (y[j] - y[k]) * (y[j] - y[k]);
    				longki = Math.sqrt(longki2);
    				longkj = Math.sqrt(longkj2);
    				coskij = prodscal / (longki * longkj); /* on veut le cosinus le plus petit possible */
    				
    				if (coskij < cosmin) {
    					cosmin = coskij; 
    					numerovoisin = k;
    				}
    			}
    		}
    	
    	return numerovoisin;
    }
    
    
    public int voisinSuivantGauche(int i,int j) { 
    	int k; 
    	int v1x, v1y, v2x, v2y, numerovoisin = -1;
    	double prodscal, longki2, longki, longkj2, longkj, coskij, cosmin, det;
    	cosmin=1;
    	
    	for (k = 0; k < x.length; k++) 
    		if (k != i && k != j) { 
    			v1x = x[j] - x[i];
    			v1y = y[j]-y[i];
    			v2x = x[k] - x[i];
    			v2y = y[k]-y[i]; 
    			det = v1x * v2y - v1y * v2x;
    			
    			if (det > 0) /* on cherche un point à droite */ { 
    				prodscal = (x[i] - x[k]) * (x[j] - x[k]) + (y[i] - y[k]) * (y[j] - y[k]);
    				longki2 = (x[i] - x[k]) * (x[i] - x[k]) + (y[i] - y[k]) * (y[i] - y[k]);
    				longkj2 = (x[j] - x[k]) * (x[j] - x[k]) + (y[j] - y[k]) * (y[j] - y[k]);
    				longki = Math.sqrt(longki2);
    				longkj = Math.sqrt(longkj2);
    				coskij = prodscal / (longki * longkj); /* on veut le cosinus le plus petit possible */
    				
    				if (coskij < cosmin) {
    					cosmin = coskij; 
    					numerovoisin = k;
    				}
    			}
    		}
    	
    	return numerovoisin;
    }

}
