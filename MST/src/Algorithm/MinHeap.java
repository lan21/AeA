package Algorithm;

import java.util.ArrayList;
import java.util.List;

public class MinHeap<T extends Comparable<T>> {
	private ArrayList<T> tas;
	
	public MinHeap(int size){
		this.tas = new ArrayList<T>(size);
	}
	
	/**
	 * construit un tas à partir d'une liste d'élément
	 * @param elements
	 */
	public MinHeap(List<T> elements){
		this.tas = new ArrayList<T>(elements.size());
		for(T element:elements){
			this.ajouter(element);
		}
	}
	
	public MinHeap(){
		this.tas = new ArrayList<T>();
	}
	
	/**
	 * ajout un élément au tas
	 * @param element
	 */
	public void ajouter(T element){
		this.tas.add(element);
		percolationUp(this.tas.size()-1);
	}
	
	/**
	 * Retire la racine du tas et le renvoie
	 * @return Le minimum dans le tas
	 */
	public T retirerRacine(){
		T element = this.tas.get(0);
		this.tas.set(0, this.tas.get(this.tas.size()-1));
		this.tas.remove(this.tas.size()-1);
		if(this.tas.size()>0){
			percolationDown(0);
		}
		return element;		
	}
	
	/**
	 * Retire un élément du tas et le renvoie
	 * @param 
	 * @return L'élément retiré du tas
	 */
	public T retirer(T element){
		int index = this.tas.indexOf(element);
		this.tas.set(index, this.tas.get(this.tas.size()-1));
		this.tas.remove(this.tas.size()-1);
		if(this.tas.size()>0){
			percolationDown(index);
		}
		return element;
	}
	
	public void changeValue(T ancientValue,T newValue){
		this.retirer(ancientValue);
		this.ajouter(newValue);
	}
	
	/**
	 * permet de remonter un noeud tant qu'il est inférieur à son père
	 * Le père d'un noeud est toujours à l'indice (indiceElement-1)/2
	 */
	private void percolationUp(int indiceElement){
		T element = this.tas.get(indiceElement);
		int indicePere;
		while(indiceElement>0){
			indicePere = (indiceElement-1)/2;
			T pere = this.tas.get(indicePere);
			if(element.compareTo(pere)<0){
				this.tas.set(indiceElement, pere);
				indiceElement = indicePere;
			}
			else{
				break;
			}
		}
		this.tas.set(indiceElement, element);
	}
	
	/**
	 * permet de descendre un noeud tant qu'il est supérieur à son fils
	 * Les fils sont toujours à l'indice 2*indiceElement+1 et 2*indiceElement+2
	 */
	private void percolationDown(int indiceElement){
		T element = this.tas.get(indiceElement);
		int indiceFils1;
		int indiceFils2;
		indiceFils1 = 2*indiceElement+1;
		indiceFils2 = 2*indiceElement+2;
		//tant que l'élément a un fils
		while(indiceFils1<this.tas.size()&&indiceFils2<this.tas.size()){			
			T fils1 = this.tas.get(indiceFils1);
			T fils2 = this.tas.get(indiceFils2);
			if(fils1.compareTo(fils2)<0 && element.compareTo(fils1)>0){
				this.tas.set(indiceElement, fils1);
				indiceElement = indiceFils1;
			}
			else if(fils2.compareTo(fils1)<0 && element.compareTo(fils2)>0){
				this.tas.set(indiceElement, fils2);
				indiceElement = indiceFils2;
			}
			else
				break;
			indiceFils1 = 2*indiceElement+1;
			indiceFils2 = 2*indiceElement+2;
		}
		//si l'élément n'a qu'un fils
		if(indiceFils1<this.tas.size()){
			T fils1 = this.tas.get(indiceFils1);
			if(element.compareTo(fils1)>0){
				this.tas.set(indiceElement, fils1);
				indiceElement = indiceFils1;
			}
		}
		this.tas.set(indiceElement, element);
	}

	public ArrayList<T> getTas() {
		return tas;
	}
	
	/**
	 * retrun the minimum without removing it from the structure
	 * @return the minimum
	 */
	public T min(){
		return this.tas.get(0);
	}
	
	public void clear(){
		this.tas.clear();
	}
	
	/**
	 * 
	 * @return true if empty, false otherwise
	 */
	public boolean isEmpty(){
		return this.tas.size()==0;
	}
	
	public boolean contains(Object element){
		return tas.contains(element);
	}
}
