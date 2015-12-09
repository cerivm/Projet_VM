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
    /*======No violations of SLA because the high availability of the Hosts in the datacenter===============*/
    /*======================================================================================================*/
    @Override
    public boolean allocateHostForVm(Vm vm) {

        int RAM;
        int Treshold_MIPS = 1100; // In order to improve the no SLA violation algorithm,
        					   // we had to make a threshold on every HOST to let the requiring MIPS free for the hosted VMS at any TIME .
        for (Host h : getHostList()) {

        	if(h.getAvailableMips() > Treshold_MIPS){
        		if (vm.getMips() < h.getAvailableMips()   &&   vm.getRam() < h.getRamProvisioner().getAvailableRam()) {
            	
        			System.out.println("VM " +vm.getId()+ " Requests ==> " + vm.getMips() +" Mips " + "And " +vm.getRam()+ " RAM" +"  ||   The Host " +h.getId()+ " has ==> " +h.getAvailableMips()+ " Mips" + " And "+h.getRamProvisioner().getAvailableRam() + " RAM");
        		
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
