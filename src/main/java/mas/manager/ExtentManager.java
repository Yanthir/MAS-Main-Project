package mas.manager;

import mas.model.business.*;
import mas.model.dto.*;
import mas.service.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ExtentManager implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        List<BatchDTO> batchDTOS = BatchService.loadBatches();
        List<BeverageDTO> beverageDTOS = BeverageService.loadBeverages();
        List<ClientDTO> clientDTOS = ClientService.loadClients();
        List<EmployeeDTO> employeeDTOS = EmployeeService.loadEmployees();
        List<IngredientDTO> ingredientDTOS = IngredientService.loadIngredients();
        List<IngredientVolumeDTO> ingredientVolumeDTOS = IngredientVolumeService.loadIngredientVolumes();
        List<OrderDTO> orderDTOS = OrderService.loadOrders();
        List<RecipeDTO> recipeDTOS = RecipeService.loadRecipes();
        List<ReportDTO> reportDTOS = ReportService.loadReports();
        List<SetDTO> setDTOS = SetService.loadSets();
        List<SubscriptionDTO> subscriptionDTOS = SubscriptionService.loadSubscriptions();
        List<SupplierDTO> supplierDTOS = SupplierService.loadSuppliers();
        List<TemplateDTO> templateDTOS = TemplateService.loadTemplates();
        List<TemplateQuantityDTO> templateQuantityDTOS = TemplateQuantityService.loadTemplateQuantities();
        List<VesselDTO> vesselDTOS = VesselService.loadVessels();
        for(BatchDTO batchDTO : batchDTOS) {
            Batch batch = (Batch) Batch.getById(Batch.class, batchDTO.getId());
            if(batch == null) {
                continue;
            }
            batch.createLinks(batchDTO);
        }
        for(BeverageDTO beverageDTO : beverageDTOS) {
            Beverage beverage = (Beverage) Beverage.getById(Beverage.class, beverageDTO.getId());
            if(beverage == null) {
                continue;
            }
            beverage.createLinks(beverageDTO);
        }
        for(ClientDTO clientDTO : clientDTOS) {
            Client client = (Client) Client.getById(Client.class, clientDTO.getId());
            if(client == null) {
                continue;
            }
            client.createLinks(clientDTO);
        }
        for(EmployeeDTO employeeDTO : employeeDTOS) {
            Employee employee = (Employee) Employee.getById(Employee.class, employeeDTO.getId());
            if(employee == null) {
                continue;
            }
            employee.createLinks(employeeDTO);
        }
        for(IngredientDTO ingredientDTO : ingredientDTOS) {
            Ingredient ingredient = (Ingredient) Ingredient.getById(Ingredient.class, ingredientDTO.getId());
            if(ingredient == null) {
                continue;
            }
            ingredient.createLinks(ingredientDTO);
        }
        for(IngredientVolumeDTO ingredientVolumeDTO : ingredientVolumeDTOS) {
            IngredientVolume ingredientVolume = (IngredientVolume) IngredientVolume.getById(IngredientVolume.class, ingredientVolumeDTO.getId());
            if(ingredientVolume == null) {
                continue;
            }
            ingredientVolume.createLinks(ingredientVolumeDTO);
        }
        for(OrderDTO orderDTO : orderDTOS) {
            Order order = (Order) Order.getById(Order.class, orderDTO.getId());
            if(order == null) {
                continue;
            }
            order.createLinks(orderDTO);
        }
        for(RecipeDTO recipeDTO : recipeDTOS) {
            Recipe recipe = (Recipe) Recipe.getById(Recipe.class, recipeDTO.getId());
            if(recipe == null) {
                continue;
            }
            recipe.createLinks(recipeDTO);
        }
        for(ReportDTO reportDTO : reportDTOS) {
            Report report = (Report) Report.getById(Report.class, reportDTO.getId());
            if(report == null) {
                continue;
            }
            report.createLinks(reportDTO);
        }
        for(SetDTO setDTO : setDTOS) {
            Set set = (Set) Set.getById(Set.class, setDTO.getId());
            if(set == null) {
                continue;
            }
            set.createLinks(setDTO);
        }
        for(SubscriptionDTO subscriptionDTO : subscriptionDTOS) {
            Subscription subscription = (Subscription) Subscription.getById(Subscription.class, subscriptionDTO.getId());
            if(subscription == null) {
                continue;
            }
            subscription.createLinks(subscriptionDTO);
        }
        for(SupplierDTO supplierDTO : supplierDTOS) {
            Supplier supplier = (Supplier) Supplier.getById(Supplier.class, supplierDTO.getId());
            if(supplier == null) {
                continue;
            }
            supplier.createLinks(supplierDTO);
        }
        for(TemplateDTO templateDTO : templateDTOS) {
            Template template = (Template) Template.getById(Template.class, templateDTO.getId());
            if(template == null) {
                continue;
            }
            template.createLinks(templateDTO);
        }
        for(TemplateQuantityDTO templateQuantityDTO : templateQuantityDTOS) {
            TemplateQuantity templateQuantity = (TemplateQuantity) TemplateQuantity.getById(TemplateQuantity.class, templateQuantityDTO.getId());
            if(templateQuantity == null) {
                continue;
            }
            templateQuantity.createLinks(templateQuantityDTO);
        }
        for(VesselDTO vesselDTO : vesselDTOS) {
            Vessel vessel = (Vessel) Vessel.getById(Vessel.class, vesselDTO.getId());
            if(vessel == null) {
                continue;
            }
            vessel.createLinks(vesselDTO);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
