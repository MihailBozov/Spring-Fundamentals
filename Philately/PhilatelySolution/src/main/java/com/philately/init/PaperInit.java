package com.philately.init;

import com.philately.model.PaperName;
import com.philately.model.entity.Paper;
import com.philately.repository.PaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class PaperInit implements CommandLineRunner {

    private final PaperRepository paperRepository;

    Map<PaperName, String> map = new LinkedHashMap<>();

    @Autowired
    public PaperInit(PaperRepository paperRepository) {
        this.paperRepository = paperRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        map.put(PaperName.WOVE_PAPER, "Has an even texture without any particular distinguishing features.");
        map.put(PaperName.LAID_PAPER, "When held up to the light, shows parallel lines of greater or less width running across the stamp.");
        map.put(PaperName.GRANITE_PAPER, "Has tiny specks of coloured fibre in it, which can usually be seen with the naked eye.");

        if (paperRepository.count() == 0) {
            for (Map.Entry<PaperName, String> entry : map.entrySet()) {
                this.paperRepository.saveAndFlush(new Paper(entry.getKey(), entry.getValue()));
            }
        }
    }
}
