package com.paintingscollectors.init;

import com.paintingscollectors.model.entity.Style;
import com.paintingscollectors.model.enums.StyleName;
import com.paintingscollectors.repository.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StylesInit implements CommandLineRunner {

    private StyleRepository styleRepository;

    private final Map<StyleName, String> description;

    @Autowired
    public StylesInit(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;

        this.description = new HashMap<>();
        this.description.put(StyleName.IMPRESSIONISM, "Impressionism is a painting style most commonly associated with the 19th century where small brush strokes are used to build up a larger picture.");
        this.description.put(StyleName.ABSTRACT, "Abstract art does not attempt to represent recognizable subjects in a realistic manner.");
        this.description.put(StyleName.EXPRESSIONISM, "Expressionism is a style of art that doesn't concern itself with realism.");
        this.description.put(StyleName.SURREALISM, "Surrealism is characterized by dreamlike, fantastical imagery that often defies logical explanation.");
        this.description.put(StyleName.REALISM, "Also known as naturalism, this style of art is considered as 'real art' and has been the dominant style of painting since the Renaissance.");

    }

    @Override
    public void run(String... args) throws Exception {
        if (this.styleRepository.count() == 0) {

            for (Map.Entry<StyleName, String> entry : this.description.entrySet()) {
                Style style = new Style(entry.getKey(), entry.getValue());
                this.styleRepository.saveAndFlush(style);
            }
        }
    }
}
