package fr.unice.vicc;

import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.VmAllocationPolicy;
import org.cloudbus.cloudsim.power.PowerHost;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Azzelarab & Med 4/12/2015.
 */
public class NextFit extends VmAllocationPolicy {

    /** The map to track the server that host each running VM. */
    private Map<Vm,Host> hoster;

    public NextFit(List<? extends Host> list) {
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
    /*==========================Next Fit Algorithm=================================*/
    /*=============================================================================*/
    int temp = 0;// Global Variable to keep any changes while iterating the hosts
    
    /* The Scheduling algorithm "Next fit" aims to allocate VM with regards to the next host from the last host allocated 
     * to the previous VM */
    @Override  
    public boolean allocateHostForVm(Vm vm) {

         for (Host h : getHostList()) {
        	
        	if(temp == h.getId())
        	{		        	           	
        			if (h.vmCreate(vm)) {
        				
        				    System.out.println("VM " + vm.getId() + " =====>   Host " + h.getId());
        					hoster.put(vm, h);
        					temp++;
        					if(temp >= getHostList().size())
        	        		{
        					   temp = 0;
        	        		}
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
