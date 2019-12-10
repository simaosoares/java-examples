package com.example.service;

import com.example.domain.Detail;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

abstract class DetailMappingUtils {

    /**
     * Map input details to the output collection.
     * Add new input lines and remove surplus output lines.
     * Match rows by index.
     * @param input input collections
     * @param output output collection
     */
    static void mapDetails(List<Detail> input, List<Detail> output){

        for (int i = 0; i <  input.size(); i++){
            Detail inputDetail = input.get(i);

            if(output.size() > i){
                // update line
                Detail lineToUpdate = output.get(i);
                lineToUpdate.setName(inputDetail.getName());
            } else {
                // add new line
                Detail newLine = new Detail(inputDetail.getName());
                output.add(newLine);
            }
        }

        // remove surplus lines
        if(input.size() < output.size()){
            output.subList(input.size(), output.size()).clear();
        }

    }

    /**
     * Map input details to the output collection.
     * Add new input lines and remove surplus output lines not matched.
     * Uses line id to match rows.
     * @param input input collections
     * @param output output collection
     */
    static void mapDetailsById(List<Detail> input, List<Detail> output){

        List<Detail> usedOrUpdatedLines = new ArrayList<>();

        for (final Detail inputDetail : input) {

            if (inputDetail.getId() == null) {
                // new line
                Detail newLine = new Detail(inputDetail.getName());
                output.add(newLine);
                usedOrUpdatedLines.add(newLine);
            } else {
                // update line
                List<Detail> result = output
                        .stream()
                        .filter(line -> line.getId().equals(inputDetail.getId()))
                        .collect(Collectors.toList());

                if (result.size() > 1) {
                    throw new IllegalStateException("Found more than one line with ID: " + inputDetail.getId());
                }

                if (result.size() == 0) {
                    throw new IllegalStateException("Unable to find line with ID: " + inputDetail.getId());
                }

                Detail detailToUpdate = result.get(0);
                detailToUpdate.setName(inputDetail.getName());

                usedOrUpdatedLines.add(detailToUpdate);
            }
        }

        // remove surplus lines
        output.retainAll(usedOrUpdatedLines);
    }

}
