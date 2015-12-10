package fr.unice.vicc;

import org.cloudbus.cloudsim.VmAllocationPolicy;
import org.cloudbus.cloudsim.power.PowerHost;

import java.util.List;

/**
 * @author Fabien Hermenier
 */
public class VmAllocationPolicyFactory {

    /**
     * Return the VMAllocationPolicy associated to id
     * @param id the algorithm identifier
     * @param hosts the host list
     * @return the selected algorithm
     */
<<<<<<< HEAD

   
	VmAllocationPolicy make(String id, List<PowerHost> hosts) {

        switch (id) {
            case "naive":  return new NaiveVmAllocationPolicy(hosts);
			case "antiAffinity": return new SopportHighlyAvailableApps(hosts);
			case "NextFit": return new NextFit(hosts);
			case "balance": return new Balance(hosts);
			case "Balance_RAM_MIPS": return new Balance_RAM_MIPS(hosts);
			case "noViolations": return new NoViolations(hosts);
        }
        throw new IllegalArgumentException("No such policy '" + id + "'");
    }
	
=======
    VmAllocationPolicy make(String id, List<PowerHost> hosts) {

        switch (id) {
            case "naive":  return new NaiveVmAllocationPolicy(hosts);
			//case "SHAA": return new SopportHighlyAvailableApps();
        }
        throw new IllegalArgumentException("No such policy '" + id + "'");
    }
>>>>>>> 3cbf478b2ae0d4436c893bd18ea77cdc5b250154
}
