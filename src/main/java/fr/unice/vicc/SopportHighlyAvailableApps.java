package fr.unice.vicc;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.VmAllocationPolicy;

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
    public List<Map<String, Object>> optimizeAllocation2(List<? extends Vm> list) {
        return null;
    }

    @Override
    public void deallocateHostForVm(Vm vm) {
        Host h = hoster.get(vm);
        h.vmDestroy(vm);
    }
    
    // Function to check if the VM ID exists already in the host ans if the host has the ressources 
    public boolean check_id(int vm_id,List list_Vm ){

    	int tableau[][] =  { {0,99}, {100,199}, {200,299}, {300,399}, {400,499}, {500,599}, {600,699}, {700,799}, {800,899}, {900,999}, {1000,51} };
    	
    	Iterator iterate = list_Vm.iterator();
    	
    	while(iterate.hasNext()){
    		
    		  int var = (int)iterate.next();
    		  
    		  for( int i = 0; i< tableau.length; ++i){
    			  
    			     for( int j = 0; j<tableau[i].length; ++j){
    			    	  
    			    	 if( tableau[i][j] <= var && tableau[i][j] <= vm_id  && tableau[i+1][j] >= var && tableau[i+1][j] >= vm_id ){
    			    		 
    			    	 }
    			    	 
    			     }
   			  }	
    		
    	}
    	return false;
    }
    
	@Override
	public boolean allocateHostForVm(Vm vm) {
				
        for (Host h : getHostList()) {
        	
        	int id_vm = vm.getId();
        	
        	System.out.println("Host List : " + h);
        	
        	h.getVmList();
            
        	if (check_id(id_vm,h.getVmList()) == true && h.vmCreate(vm) == true) {
        		
                //Suffisament de ressources, et la VM est déja  lancée
                hoster.put(vm, h);
                return true;
            }

        }
		return false;
	}

	@Override
	public boolean allocateHostForVm(Vm arg0, Host arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Host getHost(Vm arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Host getHost(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> optimizeAllocation(List<? extends Vm> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void affinity(){
		 for (Host h : getHostList()) {
	            
	        }
	}
	//List<T> list;
	
}
