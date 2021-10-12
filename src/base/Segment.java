package base;

public class Segment {
	public Point debut;
	public Point fin;

	public Segment(Point debut, Point fin) {
		super();
		this.debut = debut;
		this.fin = fin;
	}

	@Override
	public String toString() {
		return "[" + debut + ", " + fin + "]";
	}

	public Vecteur toVecteur() {
		return new Vecteur(fin.x-debut.x,fin.y-debut.y);
	}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (this.getClass() != obj.getClass()) return false;
        Segment o = (Segment) obj;
        return ((o.debut.equals(this.debut) && o.fin.equals(this.fin)) || (o.debut.equals(this.fin) && o.fin.equals(this.debut)));
    }
}
