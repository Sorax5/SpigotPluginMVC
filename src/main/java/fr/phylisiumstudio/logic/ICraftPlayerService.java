package fr.phylisiumstudio.logic;

import fr.phylisiumstudio.logic.models.CraftPlayer;

import java.util.List;
import java.util.UUID;

/**
 * Service interface for CraftPlayer with CRUD operations
 */
public interface ICraftPlayerService {
    /**
     * Create a new CraftPlayer
     * @param craftPlayer the CraftPlayer to create
     */
    void create(CraftPlayer craftPlayer);

    /**
     * Read a CraftPlayer by its id
     * @param id the id of the CraftPlayer
     * @return the CraftPlayer
     */
    CraftPlayer read(UUID id);

    /**
     * Update a CraftPlayer
     * @param craftPlayer the CraftPlayer to update
     */
    void update(CraftPlayer craftPlayer);

    /**
     * Delete a CraftPlayer by its id
     * @param id the id of the CraftPlayer
     */
    void delete(UUID id);

    /**
     * List all CraftPlayer
     * @return the list of CraftPlayer
     */
    List<CraftPlayer> list();
}
