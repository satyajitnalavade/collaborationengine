package com.example.application.views.shoppinglist;

import com.example.application.data.entity.ShoppingListItem;
import com.vaadin.collaborationengine.CollaborationBinder;
import com.vaadin.collaborationengine.UserInfo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;

import java.util.List;

public class ItemForm extends HorizontalLayout {

    private static final List<String> CATEGORIES = List.of("Produce", "Deli", "Bakery", "Meat & Seafood", "Dairy, Cheese & Eggs", "Breakfast", "Coffee & Tea",
        "Nut Butters, Honey & Jam", "Baking & Spices", "Rice, Grains & Beans", "Canned & Jarred Goods",
        "Pasta & Sauces", "Oils, Sauces & Condiments", "International", "Frozen", "Snacks",
        "Nuts, Seeds & Dried Fruit", "Candy", "Beverages", "Wine, Beer & Spirits", "Personal Care", "Health",
        "Baby", "Household", "Kitchen", "Cleaning Products", "Pet Care", "Party", "Floral", "Other");

    private final IntegerField amount = new IntegerField();
    private final TextField name = new TextField();
    private final ComboBox<String> category = new ComboBox<>();
    private final CollaborationBinder<ShoppingListItem> binder;
    private final Button button = new Button("Add");
    private ShoppingListItem item;

    @FunctionalInterface
    public interface SaveHandler {
        void itemSaved(ShoppingListItem item);
    }

    @FunctionalInterface
    public interface DeleteHandler {
        void itemDeleted(ShoppingListItem item);
    }

    public ItemForm(ShoppingListItem item, UserInfo userInfo) {
        addClassName("item-form");
        setAlignItems(Alignment.BASELINE);
        category.setItems(CATEGORIES);
        amount.setPlaceholder("Amount");
        name.setPlaceholder("Item");
        category.setPlaceholder("Category");
        add(amount, name, category, button);
        binder = new CollaborationBinder<>(ShoppingListItem.class, userInfo);
        binder.bindInstanceFields(this);
        setItem(item);
    }

    public void setSaveHandler(SaveHandler saveHandler) {
        button.addClickListener(click -> {
            if (binder.writeBeanIfValid(item)) {
                saveHandler.itemSaved(item);
            }
        });
    }

    public void setItem(ShoppingListItem item) {
        this.item = item;
        var id = item.getId() == null ? "new" : item.getId().toString();
        binder.setTopic("item/" + id, () -> item);

        if (item.getId() != null) {
            button.setText("Update");
            addClassName("existing");
        } else {
            button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        }
    }

    public void reset(ShoppingListItem item) {
        this.item = item;
        binder.reset(item);
    }

    public void setDeleteHandler(DeleteHandler deleteHandler) {
        var deleteButton = new Button("Delete", e -> deleteHandler.itemDeleted(item));
        deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        add(deleteButton);
    }
}
