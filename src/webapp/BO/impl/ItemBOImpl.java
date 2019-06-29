package webapp.BO.impl;

import webapp.BO.ItemBO;
import webapp.DAO.ItemDAO;
import webapp.DAO.impl.ItemDAOImpl;
import webapp.dto.ItemDTO;
import webapp.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {

    private ItemDAO itemDAO;

    public ItemBOImpl() {
        itemDAO = new ItemDAOImpl();
    }

    @Override
    public boolean createItem(ItemDTO itemDTO) {
        return itemDAO.createItem(new Item(itemDTO.getItemCode(),
                itemDTO.getItemName(),
                itemDTO.getItemQty(),
                itemDTO.getItemPrice()));
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) {
        return itemDAO.updateItem(new Item(itemDTO.getItemCode(),
                itemDTO.getItemName(),
                itemDTO.getItemQty(),
                itemDTO.getItemPrice()));
    }

    @Override
    public List<ItemDTO> getAllItems() {
        List<Item> allItems = itemDAO.getAllItems();
        List<ItemDTO> itemDTOS = new ArrayList<>();
        if (allItems != null || !allItems.isEmpty()) {
            allItems.forEach(item -> {
                itemDTOS.add(new ItemDTO(item.getItemCode(),
                        item.getItemName(), item.getItemQty(), item.getItemPrice()));
            });
        }
        return itemDTOS;
    }

    @Override
    public boolean deleteItem(String itemCode) {
        return itemDAO.deleteItem(itemCode);
    }
}
