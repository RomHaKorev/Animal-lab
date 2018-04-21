package app.games.dim.animallab.model.structures;


import java.util.HashMap;
import java.util.List;

import app.games.dim.animallab.model.Mutation;

/**
 * Created by Dim on 15/04/2018.
 */

public class BeastStructure {

    private BeastTemplate m_currentTemplate;
    private HashMap<BeastTemplate.EPart, BeastTemplate.EType> m_parts;

    public BeastStructure() {
        BeastTemplate.register(new Pig());
        BeastTemplate.register(new Pigv2());
        BeastTemplate.register(new Insect());
        BeastTemplate.register(new Flesh());
        BeastTemplate.register(new Goat());
        BeastTemplate.register(new Crab());
         m_currentTemplate = BeastTemplate.get(BeastTemplate.EType.PIG);
         m_parts = new HashMap<BeastTemplate.EPart, BeastTemplate.EType>();
         for (BeastTemplate.EPart id: BeastTemplate.EPart.values()) {
             m_parts.put(id, BeastTemplate.EType.PIG);
         }
    }

    public Integer layout() {
        return m_currentTemplate.layout();
    }

    public Integer image(BeastTemplate.EPart id) {
        BeastTemplate.EType img_type = m_parts.get(id);
        return BeastTemplate.get(img_type).image(id);
    }


    public Mutation mutate() {
        List<BeastTemplate.EPart> random_part_list = BeastTemplate.EPart.shuffle();
        List<BeastTemplate.EType> random_type_list = BeastTemplate.EType.shuffle();
        for (BeastTemplate.EPart random_part: random_part_list) {
            for (BeastTemplate.EType random_type: random_type_list) {
                if (random_type == m_parts.get(random_part))
                    continue;
                BeastTemplate tml = BeastTemplate.get(random_type);
                if (tml == null)
                    continue;
                Integer potential_img = tml.image(random_part);
                if (potential_img != -1) {
                    m_parts.remove(random_part);
                    m_parts.put(random_part, random_type);
                    return new Mutation(random_part, potential_img);
                }
            }
        }
        return new Mutation(BeastTemplate.EPart.NONE, -1);
    }
}
