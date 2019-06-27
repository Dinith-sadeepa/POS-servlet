package webapp.BO;

import webapp.dto.ItemDTO;

import java.util.List;

public interface ItemBO {
    boolean createItem(ItemDTO itemDTO);

    boolean updateItem(ItemDTO itemDTO);

    List<ItemDTO> getAllItems();

    boolean deleteItem(String itemCode);
}
