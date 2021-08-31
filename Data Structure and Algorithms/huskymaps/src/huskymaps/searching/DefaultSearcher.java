package huskymaps.searching;

import arrayutils.ArraySearcher;
import arrayutils.BinaryRangeSearcher;
import autocomplete.Autocomplete;
import autocomplete.DefaultTerm;
import autocomplete.Term;
import huskymaps.graph.Node;
import huskymaps.graph.StreetMapGraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @see Searcher
 */
public class DefaultSearcher extends Searcher {
    private StreetMapGraph graph;
    private Autocomplete locations;
    private List<Node> names;
    private Term[] terms;

    public DefaultSearcher(StreetMapGraph graph) {
        this.graph = graph;
        names = new ArrayList<>();
        for (Node node: graph.allNodes()) {
            if (node.name() != null) {
                names.add(node);
            }
        }
        terms = new Term[names.size()];
        for (int i = 0; i < names.size(); i++) {
            terms[i] = createTerm(names.get(i).name(), names.get(i).importance());
        }
        this.locations = createAutocomplete(terms);
    }

    @Override
    protected Term createTerm(String name, int weight) {
        return new DefaultTerm(name, weight);
    }

    @Override
    protected Autocomplete createAutocomplete(Term[] termsArray) {
        return new Autocomplete(termsArray);
    }

    @Override
    public List<String> getLocationsByPrefix(String prefix) {
        Set<String> matches = new HashSet<>();
        for (Node node : names) {
            if (node.name().length() >= prefix.length() && node.name().substring(0, prefix.length()).equals(prefix)) {
                matches.add(node.name());
            }
        }
        return new ArrayList<>(matches);
    }

    @Override
    public List<Node> getLocations(String locationName) {
        List<Node> matches = new ArrayList<>();
        List<Node> allNodes = graph.allNodes();
        Node[] arrayNode = new Node[allNodes.size()];
        for (int i = 0; i < allNodes.size(); i++) {
            arrayNode[i] = allNodes.get(i);
        }
        ArraySearcher.Matcher<Node, String> matcher = (matchee, target) ->
            matchee.name().compareTo(target);
        BinaryRangeSearcher<Node, String> bisearch = new BinaryRangeSearcher(arrayNode, matcher);
        BinaryRangeSearcher.MatchResult match = bisearch.findAllMatches(locationName);
        Node[] matchedNodes = (Node[]) match.unsorted();
        for (int i = 0; i < matchedNodes.length; i++) {
            matches.add(matchedNodes[i]);
        }
        return matches;
    }
}
