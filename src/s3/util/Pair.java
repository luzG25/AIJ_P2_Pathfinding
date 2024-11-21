package s3.util;

import java.util.Objects;

public class Pair<T1,T2> {
	public T1 m_a;
	public T2 m_b;
	
	public Pair(T1 a,T2 b) {
		m_a = a;
		m_b = b;
	}

	public boolean isEqual(Pair<T1, T2> other) {
		if (other == null) return false; // Verifica se o objeto comparado é null
	
		boolean aEquals = (this.m_a == null && other.m_a == null) || 
						  (this.m_a != null && this.m_a.equals(other.m_a));
		boolean bEquals = (this.m_b == null && other.m_b == null) || 
						  (this.m_b != null && this.m_b.equals(other.m_b));
		
		return aEquals && bEquals;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true; // Verifica se são o mesmo objeto
        if (o == null || getClass() != o.getClass()) return false;

        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(m_a, pair.m_a) && Objects.equals(m_b, pair.m_b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_a, m_b);
    }

	public void print(double heuristica){
		System.out.println("x" + this.m_a + "y" + this.m_b + "  heuristica: " + heuristica);
	}
	
}
