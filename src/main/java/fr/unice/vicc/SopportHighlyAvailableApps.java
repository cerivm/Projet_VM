package fr.unice.vicc;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.VmAllocationPolicy;

/**
 * Created by Azzelarab & Med 4/12/2015.
 */

public class SopportHighlyAvailableApps extends VmAllocationPolicy{

    /** The map to track the server that host each running VM. */
    private Map<Vm,Host> hoster;

    public SopportHighlyAvailableApps(List<? extends Host> list) {
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

    //@Override
    public List<Map<String, Object>> optimizeAllocation(List<? extends Vm> list) {
        return null;
    }
/*==============================================================================================================*/
/*==============================================================================================================*/
    // Function to check if the VM ID exists already in the host and if the host has the ressources     
    public boolean check_id(int vm_id,List<Vm> list_Vm ){  	
   	   	
    	int temp = 0;
    	int vm_host = 0;
    	int unreachable = 2000;
        int min = 0;
        int max = 99;
        
    	while( temp <= list_Vm.size()-1 ){ 
    		
    			vm_host = list_Vm.get(temp).getId();        				

       		  		if( vm_host >= min && vm_host <= max      &&      vm_id >= min && vm_id <= max ){

       		  			return true;
       		  		}            		  		
       		  		
   		  	 min = min + 100;
   		  	 max = max + 100;
    	  ++temp;
    	}

    	return false;
    }
    
/* public boolean check_id(int vm_id,List<Vm> list_Vm ){  	
   	   	
    	int temp = 0;
    	int vm_host = 0;
        
    	while( temp <= list_Vm.size()-1 ){    		
  
              vm_host = list_Vm.get(temp).getId() / 100;
              
              if((vm_id / 100) == vm_host){
            	  return true;
              }
    	}
    	return false;
    }*/
    
	@Override
	public boolean allocateHostForVm(Vm vm) {
			
		int id_vm = vm.getId();
		//List<Vm> list_Vm;
		
        for (Host h : getHostList()) {
        	        	
        	//list_Vm = h.getVmList(); 
        	       		
        		if (check_id(id_vm, h.getVmList()) == false ){
        			
        			if(h.vmCreate(vm)){
        		
        				System.out.println("VM " + vm.getId() + " =====> " + h.getId());
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
