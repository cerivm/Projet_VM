package fr.unice.vicc;

import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.VmAllocationPolicy;
import org.cloudbus.cloudsim.power.PowerDatacenter;
import org.cloudbus.cloudsim.power.PowerHost;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Azzelarab & Med 11/12/2015.
 */
public class StatEnergy extends VmAllocationPolicy {

    /** The map to track the server that host each running VM. */
    private Map<Vm,Host> hoster;

    public StatEnergy(List<? extends Host> list) {
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

    /*======================================================================================================================*/
    /*======================================================================================================================*/
        // ***********************Function to check if the VM ID exists already in the host**************************  
        // The Principle of our algorithm is to check if the New VM has the ID in the same interval by looking at the result of
        // the division of New VM ID by "100" and compare it with the division of "EVERY" VM ID already existing in the Host.
            
       public boolean check_id(int vm_id,List<Vm> list_Vm ){  	
       	   	
        	int i = 0;
        	int vm_host = 0; // A variable to count the number of VMs in the Host 
        	int temp = vm_id / 100;  // Division Of the new VM by "100"
        	Map<Integer, Integer> TheMap = new HashMap<>();
        	
        	//Iterate the Host VMs
    		for(i=0; i <= list_Vm.size()-1; i++)
    		{
    			int var = list_Vm.get(i).getId() / 100;		
    			// checking if the IDs are equal and then return True : 
    			// that means the schedulers should not allocate the VM to that Host
    			if(temp == var){
    				TheMap.put(list_Vm.get(i).getId(), vm_host);
    				return true;
    			}
    		
    			vm_host++;
    		}
            
         return false;
        }
        //===========================================================================================================
    	//===========================================================================================================
    	// The resulting simulation of this Scheduler consumes less energy than all the previous schedulers.

    	@Override
    	public boolean allocateHostForVm(Vm vm) {
    			
    		int id_vm = vm.getId();
    		//List<Vm> list_Vm;
    		
            for (Host h : getHostList()) {        	        	
            	//list_Vm = h.getVmList(); 
            	       		
            		if (check_id(id_vm, h.getVmList()) == false ){
            			
            			// Without relying on Vm migration
            			h.removeMigratingInVm(vm);
            			
            			if(h.vmCreate(vm)){
            		
            				System.out.println("VM ID " + vm.getId() + "Hosted By =====> " + h.getId());
            				hoster.put(vm, h);
            			
            			 return true;
            			}
            		}    
            }

    		return false;
    	}
    /*==============================================================================================================*/
    /*==============================================================================================================*/
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
