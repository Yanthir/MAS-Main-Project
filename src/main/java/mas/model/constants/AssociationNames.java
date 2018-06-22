package mas.model.constants;

public class AssociationNames {
    public static final String
        ASSOC_REPORT_BATCH = "reportTargetBatch",
        ASSOC_REPORT_CONTROLLER = "reportController",

        ASSOC_BATCH_REPORTS = "batchReports",
        ASSOC_BATCH_RECIPE = "batchRecipe",
        ASSOC_BATCH_BEVERAGES = "batchBeverages",

        ASSOC_RECIPE_BATCHES = "recipeBatches",
        ASSOC_RECIPE_INGREDIENT_VOLUMES = "recipeIngredientVolumes",
        ASSOC_RECIPE_TEMPLATES = "recipeTemplates",

        ASSOC_INGREDIENT_VOLUME_RECIPE = "ingredientVolumeRecipe",
        ASSOC_INGREDIENT_VOLUME_INGREDIENT = "ingredientVolumeIngredient",

        ASSOC_INGREDIENT_INGREDIENT_VOLUMES = "ingredientIngredientVolumes",
        ASSOC_INGREDIENT_SUPPLIER = "ingredientSupplier",

        ASSOC_SUPPLIER_INGREDIENTS = "supplierIngredients",
        ASSOC_SUPPLIER_VESSELS = "supplierVessels",

        ASSOC_VESSEL_SUPPLIER = "vesselSupplier",
        ASSOC_VESSEL_TEMPLATES = "vesselTemplates",

        ASSOC_TEMPLATE_VESSEL = "templateVessel",
        ASSOC_TEMPLATE_RECIPE = "templateRecipe",
        ASSOC_TEMPLATE_TEMPLATE_QUANTITIES = "templateTemplateQuantities",
        ASSOC_TEMPLATE_BEVERAGES = "templateBeverages",

        ASSOC_TEMPLATE_QUANTITY_SET = "templateQuantitySet",
        ASSOC_TEMPLATE_QUANTITY_TEMPLATE = "templateQuantityTemplate",

        ASSOC_SET_TEMPLATE_QUANTITIES = "setTemplateQuantities",
        ASSOC_SET_SUBSCRIPTIONS = "setSubscriptions",

        ASSOC_CLIENT_ORDERS = "clientOrder",
        ASSOC_CLIENT_SUBSCRIPTIONS = "clientSubscription",

        ASSOC_SUBSCRIPTION_SET = "subscriptionSet",
        ASSOC_SUBSCRIPTION_CLIENT = "subscriptionClient",

        ASSOC_BEVERAGE_TEMPLATE = "beverageTemplate",
        ASSOC_BEVERAGE_ORDER = "beverageOrder",
        ASSOC_BEVERAGE_BATCH = "beverageBatch",

        ASSOC_ORDER_BEVERAGES = "orderBeverages",
        ASSOC_ORDER_CLIENT = "orderClient",

        ASSOC_CONTROLLER_REPORTS = "controllerReports";
}
