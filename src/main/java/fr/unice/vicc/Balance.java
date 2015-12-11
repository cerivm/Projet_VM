package fr.unice.vicc;

import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.VmAllocationPolicy;
import org.cloudbus.cloudsim.power.PowerHost;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Azzelarab & Med 6/12/2015.
 */
public class Balance extends VmAllocationPolicy {

    /** The map to track the server that host each running VM. */
    private Map<Vm,Host> hoster;

    public Balance(List<? extends Host> list) {
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

    /*=============================================================================*/
    /*========================Balance " MIPS Condition=============================*/
    /*=============================================================================*/
    
    // Overriding the function and checking if the Host has enough MIPS ressources
    @Override
    public boolean allocateHostForVm(Vm vm) {
    	       
        for (Host h : getHostList()) {
        	
        	// Checking if the VM required MIPS are less than the MIPS available in The Host
            if (vm.getMips() < h.getAvailableMips()) {
            	
            	System.out.println("VM " +vm.getId()+ " Requests ==> " + vm.getMips() +" Mips " + "  ||   The Host " +h.getId()+ " has ==> " +h.getAvailableMips()+ " Available Mips");
            	
            	if(h.vmCreate(vm)){            		
            		hoster.put(vm, h);
            		return true;
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
