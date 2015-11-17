package fr.unice.vicc;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Vector;

import org.cloudbus.cloudsim.Vm;

public class VmAffinity {

	private Vector<Node> nodeList; // Structure de données qui contient le noeud
								// avec sa propre liste des Vms

	public VmAffinity(Vector<Vm> list) {

		nodeList = new Vector<>();

		int z = 0;
		Integer max = 0;
		for (Vm vm : list) {
			if (vm.getId() > max) {
				max = vm.getId();
			}
		}
		for (int i = 0; i < max / 100; i++) {
			Node n = new Node();
			n.setNumber(i);
			for (int j = 0; j < list.size(); j++) {
				if (list.get(j).getId() < z + 100 && list.get(j).getId() >= z) {
					n.getList().add(list.get(j));
				}
			}
			z = z + 100;
			if (n.getList().size() > 0)
				nodeList.add(n); // l'Ajout de noeud si seulement l'objet
									// n.liste contient au moins une Vm
		}

	}

	public Vector<Node> getNodeList() {
		return nodeList;
	}

	public void setNodeList(Vector<Node> nodeList) {
		this.nodeList = nodeList;
	}

}