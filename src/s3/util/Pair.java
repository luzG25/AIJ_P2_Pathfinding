package s3.util;

public class Pair<T1,T2> {
	public T1 m_a;
	public T2 m_b;
	
	public Pair(T1 a,T2 b) {
		m_a = a;
		m_b = b;
	}

	public boolean isEqual(Pair<T1, T2> other)
	{
		if (this.m_a == other.m_a && this.m_b == other.m_b) return true;
		else return false;
	}
}
