package app.games.dim.animallab.model.structures;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;

import app.games.dim.animallab.model.Mutation;

/**
 * Created by Dim on 15/04/2018.
 */

public class BeastStructure {

    private BeastTemplate m_currentTemplate;
    private HashMap<BeastTemplate.EPart, BeastTemplate.EType> m_parts;
    private ArrayList<BeastTemplate.EPart> m_unchanged;
    private static final BeastTemplate.EType default_type = BeastTemplate.EType.PIG;
    public BeastStructure() {
        m_unchanged = new ArrayList<BeastTemplate.EPart>();
        BeastTemplate.register(new Pig());
        //BeastTemplate.register(new Pigv2());
        BeastTemplate.register(new Insect());
        BeastTemplate.register(new Flesh());
        BeastTemplate.register(new Goat());
        BeastTemplate.register(new Crab());
        BeastTemplate.register(new Misc());
        BeastTemplate.register(new Octopus());
        m_currentTemplate = BeastTemplate.get(default_type);
        m_parts = new HashMap<BeastTemplate.EPart, BeastTemplate.EType>();

        for (BeastTemplate.EPart id: BeastTemplate.EPart.values()) {
            m_parts.put(id, default_type);
            m_unchanged.add(id);
        }
        m_unchanged.remove(BeastTemplate.EPart.NONE);
    }

    public Integer layout() {
        return m_currentTemplate.layout();
    }

    public List<BeastTemplate.EPart> all_parts() {
        return new ArrayList<BeastTemplate.EPart>(m_parts.keySet());
    }

    public Integer image(BeastTemplate.EPart id) {
        BeastTemplate.EType img_type = m_parts.get(id);
        return BeastTemplate.get(img_type).image(id);
    }

    public Mutation mutate() {
        if (m_unchanged.isEmpty()) {
            for (BeastTemplate.EPart id: BeastTemplate.EPart.values()) {
                m_unchanged.add(id);
            }
            m_unchanged.remove(BeastTemplate.EPart.NONE);
        }
        List<BeastTemplate.EPart> random_part_list = BeastTemplate.EPart.shuffle();
        List<BeastTemplate.EType> random_type_list = BeastTemplate.EType.shuffle();
        for (BeastTemplate.EType random_type: random_type_list) {
            for (BeastTemplate.EPart random_part: random_part_list) {
                if (random_type == m_parts.get(random_part)
                        && random_type != BeastTemplate.EType.MISC) {
                    continue;
                }
                if (!m_unchanged.contains(random_part)) {
                    continue;
                }
                BeastTemplate tml = BeastTemplate.get(random_type);
                if (tml == null)
                    continue;
                Integer potential_img = tml.image(random_part);
                if (potential_img != -1) {
                    tml = BeastTemplate.get(m_parts.get(random_part));
                    Integer current_img = tml.image(random_part);
                    m_unchanged.remove(random_part);
                    m_parts.remove(random_part);
                    m_parts.put(random_part, random_type);
                    BeastTemplate.get(random_type).notifySelection(random_part);
                    return new Mutation(random_part, potential_img, current_img);
                }
            }
        }
        return new Mutation(BeastTemplate.EPart.NONE, -1, -1);
    }
}
