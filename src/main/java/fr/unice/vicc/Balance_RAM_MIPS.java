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
public class Balance_RAM_MIPS extends VmAllocationPolicy {

    /** The map to track the server that host each running VM. */
    private Map<Vm,Host> hoster;

    public Balance_RAM_MIPS(List<? extends Host> list) {
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
    /*======================== Balance " MIPS / RAM "==============================*/
    /*=============================================================================*/
    
    // Overriding the function and checking if the Host has enough MIPS and RAM ressources
    @Override
    public boolean allocateHostForVm(Vm vm) {

        int RAM;
        for (Host h : getHostList()) {        	

        	// Checking if the VM required MIPS / RAM are less than the MIPS and RAM available in The Host
        		if (vm.getMips() < h.getAvailableMips()   &&   vm.getRam() < h.getRamProvisioner().getAvailableRam()) {
            	
        			System.out.println("VM " +vm.getId()+ " Requests ==> " + vm.getMips() +" Mips " + "And " +vm.getRam()+ " RAM" +"  ||   The Host " +h.getId()+ " has ==> " +h.getAvailableMips()+ " Mips" + " And "+h.getRamProvisioner().getAvailableRam() + " RAM");
        		
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
