package northwind.web;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.omnifaces.util.Messages;

import lombok.Getter;
import lombok.Setter;
import northwind.entity.Order;
import northwind.service.NorthwindService;

@Named
@ViewScoped
public class OrderQuerySingleResultController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull(message="A OrderID value is required")
	@Getter @Setter private Integer queryIdValue;	// +getter +setter
	
	@Getter @Setter private Order querySingleResult;		// +getter +setter

	@Inject
	private NorthwindService northwindService;
	
	public void doSearch() {
		try {
			querySingleResult = northwindService.findOneOrder(queryIdValue);
			if(querySingleResult != null) {
				Messages.addGlobalInfo("Found 1 record");
			} else {
				Messages.addGlobalInfo("No record with {0} value", queryIdValue);
			}
		} catch (Exception e) {
			Messages.addGlobalError("Error searching for order");
			e.printStackTrace();
		}		
	}
	
	public void doCancel() {
		queryIdValue = null;
		querySingleResult = null;
	}
	
}
