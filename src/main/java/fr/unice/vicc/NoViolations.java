package fr.unice.vicc;

import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.VmAllocationPolicy;
import org.cloudbus.cloudsim.power.PowerHost;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Azzelarab & Med 7/12/2015.
 */
public class NoViolations extends VmAllocationPolicy {

    /** The map to track the server that host each running VM. */
    private Map<Vm,Host> hoster;

    public NoViolations(List<? extends Host> list) {
        super(list);
        hoster =new HashMap<>();
    }

    @Override
    protected void setHostList(List<? extends Host> hostList) {
        super.setHostList(hostList);
        hoster = new HashMap<>();
    }

    @Override
    public <T extends Host> List<T> getHostList() {
        return super.getHostList();
    }

    @Override
    public List<Map<String, Object>> optimizeAllocation(List<? extends Vm> list) {
        return null;
    }
    
    /*======================================================================================================*/
    // In order to Have no SLA Violations : The Vm accessed at any moment all the MIPS it required
    // The Principle of the algorithm is to define a MIPS Treshold / Metric for every host in the DataCenter. 
    
    @Override
    public boolean allocateHostForVm(Vm vm) {

        int RAM;
        int Treshold_MIPS = 1100; // In order to improve the no SLA violation algorithm,
        					      // we had to make a Metric on every HOST to let the requiring MIPS 
        						  // free for the hosted VMS at any TIME.
        
        for (Host h : getHostList()) {

        	if(h.getAvailableMips() > Treshold_MIPS){
        		if (vm.getMips() < h.getAvailableMips()) {
            	
        			System.out.println("VM " +vm.getId() +"  || Hosted By " +h.getId());
        		
        			if(h.vmCreate(vm)){
        				hoster.put(vm, h);
        				return true;
        			}
        		}
        	}
        }
        return false;
    }
    /*=============================================================================*/
    /*=============================================================================*/
    @Override
    public boolean allocateHostForVm(Vm vm, Host host) {
        if (host.vmCreate(vm)) {
            hoster.put(vm, host);
            return true;
        }
        return false;
    }

    @Override
    public void deallocateHostForVm(Vm vm) {
        Host h = hoster.get(vm);
        h.vmDestroy(vm);
    }

    @Override
    public Host getHost(Vm vm) {
        return hoster.get(vm);
    }

    @Override
    public Host getHost(int vmId, int userId) {
        for (Host h : getHostList()) {
            for (Vm v : h.getVmList()) {
                if (v.getUserId() == userId && v.getId() == vmId) {
                    return h;
                }
            }
        }
        return null;
    }
}
