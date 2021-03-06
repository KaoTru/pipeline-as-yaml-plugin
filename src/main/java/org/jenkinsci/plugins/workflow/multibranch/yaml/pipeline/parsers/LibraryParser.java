package org.jenkinsci.plugins.workflow.multibranch.yaml.pipeline.parsers;

import org.jenkinsci.plugins.workflow.multibranch.yaml.pipeline.exceptions.PipelineAsYamlException;
import org.jenkinsci.plugins.workflow.multibranch.yaml.pipeline.exceptions.PipelineAsYamlUnknownTypeException;
import org.jenkinsci.plugins.workflow.multibranch.yaml.pipeline.interfaces.ParserInterface;
import org.jenkinsci.plugins.workflow.multibranch.yaml.pipeline.models.LibraryModel;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

/**
 * Parser for {@link LibraryModel}
 */
public class LibraryParser extends AbstractParser implements ParserInterface<LibraryModel> {

    private LinkedHashMap parentNode;

    /**
     * @param parentNode Parent Node which contains model definition as yaml
     */
    public LibraryParser(LinkedHashMap parentNode){
        this.yamlNodeName = LibraryModel.directive;
        this.parentNode = parentNode;
    }

    @Override
    public Optional<LibraryModel> parse() {
        try {
            Object childNode = this.getChildNodeAsObject(parentNode);
            if( childNode instanceof String) {
                return Optional.of(new LibraryModel((String) childNode));
            }
            else if ( childNode instanceof List ) {
                return Optional.of(new LibraryModel((List) childNode));
            }
            else {
                throw new PipelineAsYamlUnknownTypeException(childNode.getClass().toString());
            }
        }
        catch (PipelineAsYamlException p){
            return Optional.empty();
        }
    }
}
