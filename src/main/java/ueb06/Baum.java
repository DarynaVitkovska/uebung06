package ueb06;

import java.util.Stack;

class Baum<T extends Comparable<T>> {
	private class Element {
		T value;
		Element left, right;
		Element(T value) { this.value = value; }

		void add(T value){
			int c = value.compareTo(this.value);
			if(c == 0){
				return;
			}else if(c<0){
				if(left == null){
					left =new Element(value);
				}else {
					left.add(value);
				}
			}else {
				if(right == null){
					right = new Element(value);
				}else {
					right.add(value);
				}
			}
		}

		boolean contains(T value){
			int c = value.compareTo(this.value);
			if(c == 0){
				return true;
			}else if(c < 0 && left != null){
				return left.contains(value);

			}else if(c >0 && right != null) {
				return right.contains(value);
			} else {
				return false;
			}
		}

		public String toString() {
			String s = "";
			if(left != null){
				s = s + left.toString();
			}

			s = s + value.toString() + ", ";
			if(right != null){
				s = s + right.toString();
			}

			return  s;
		}


	}

	private Element root;

	/**
	 * Fügt ein Element in den Baum ein.
	 */
	void add(T value) {
		if (root == null) {
			root = new Element(value);
			return;
		}

		Element it = root;
		while (it != null) {
			int c = value.compareTo(it.value);

			if (c == 0)
				return;
			else if (c < 0) {
				if (it.left == null) {
					it.left = new Element(value);
					return;
				} else {
					it = it.left;
				}
			} else {
				if (it.right == null) {
					it.right = new Element(value);
					return;
				} else {
					it = it.right;
				}
			}
		}
	}

	/**
	 *  Wie `add`, aber rekursiv zu implementieren.
	 */
	void addRek(T value) {
		if(root==null){
			root = new Element(value);
			return;
		}else {
			root.add(value);
		}

	}

	/**
	 * Gibt `true` zurück, wenn der Wert `value` im Baum enthalten ist.
	 */
	boolean contains(T value) {
		if (root == null)
			return false;

		Element it = root;
		while (it != null) {
			int c = value.compareTo(it.value);
			if (c == 0)
				return true;
			else if (c < 0)
				it = it.left;
			else
				it = it.right;
		}

		return false;
	}

	/**
	 * Wie `contains`, aber rekursiv zu implementieren.
	 */
	boolean containsRek(T value)
	{
		if(root == null){
			return false;
		}else {
			return root.contains(value);
		}
	}

	/**
	 * Gibt eine Stringrepraesentation des Baums zurück, wobei das Format
	 * eine aufsteigende sortierte Liste darstellt, z.B. [] oder [2, 3, 4].
	 * Abstrakt betrachtet ist dies die sog. Infixschreibweise, bei der fuer
	 * ein Element zuerst der linke Teilbaum, dann der Wert, dann der rechte
	 * Tb. ausgegeben wird.
	 */
	public String toString() {
		Stack<Element> agenda = new Stack<>();

		// Tiefenabstieg nach links
		Element it = root;
		while (it != null) {
			agenda.push(it);
			it = it.left;
		}

		StringBuilder sb = new StringBuilder();// hilf uns ein String zu bauen

		while (!agenda.empty()) { // solange diese Stack agenda noch nicht leer ist
			//empty() sagt ob die agenda voll ist
			Element e = agenda.pop(); // hol das oberste Element vom Stack
			sb.append(e.value); // hänge den Wert an den String an
			// append häng e an den String an

			// Tiefenabstieg nach rechts
			it = e.right;
			while (it != null) {
				agenda.push(it);
				it = it.left;
			}

			if (agenda.size() > 0) // solange agenda noch Elemente hat, mach sie nach jedem Element ein Komma
				sb.append(", ");
		}



		return sb.toString();
	}

	/**
	 * Zusatzaufgabe: Wie `toString`, nur rekursiv zu implementieren.
	 */
	String toStringRek() {
		if (root == null) {
			return "[]";
		}else {
			String s = "[";
			String inhalt = root.toString();
			String richtigerInhalt = root.toString().substring(0, inhalt.length()-2);//  це пропускає дві останні цифри з стрінгу
			s = s + richtigerInhalt;
			s = s + "]";

			return s;
		}
	}
}
