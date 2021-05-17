
import de.uni_mannheim.informatik.dws.melt.matching_eval.ExecutionResultSet;
import de.uni_mannheim.informatik.dws.melt.matching_eval.Executor;
import de.uni_mannheim.informatik.dws.melt.matching_eval.evaluator.EvaluatorCSV;
import de.uni_mannheim.informatik.dws.melt.matching_eval.evaluator.metric.resultsSimilarity.MatcherSimilarity;
import de.uni_mannheim.informatik.dws.melt.matching_eval.evaluator.metric.resultsSimilarity.MatcherSimilarityMetric;
import de.uni_mannheim.informatik.dws.melt.matching_eval.tracks.TrackRepository;
import de.uni_mannheim.informatik.dws.melt.matching_eval.visualization.MatcherSimilarityLatexHeatMapWriter;
import de.uni_mannheim.informatik.dws.melt.matching_eval.visualization.MatcherSimilarityLatexPlotWriter;

import java.io.PrintWriter;


public class main {
	final static String base_path = "/Users/sefika/";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			System.out.println("sefika");
		
			final String pathToConferenceResultFiles = base_path+"/automated-ontology-construction/src/Models_Tuning/model7/";
			MatcherSimilarityMetric metric = new MatcherSimilarityMetric();
			PrintWriter writer = new PrintWriter(System.out);
			MatcherSimilarityLatexPlotWriter plotWriter = new MatcherSimilarityLatexPlotWriter();
			ExecutionResultSet executionResultSetConference = Executor.loadFromConferenceResultsFolder(pathToConferenceResultFiles);
			metric = new MatcherSimilarityMetric();
	        MatcherSimilarity similarity= metric.get(MatcherSimilarityMetric.CalculationMode.MICRO, executionResultSetConference, TrackRepository.Conference.V1);
	        MatcherSimilarityLatexHeatMapWriter.write(similarity, writer);

	        //--------------------------------------------------------
	        // MAD Calculation Conference
	        //--------------------------------------------------------
	        plotWriter.write(similarity, writer);
	
	        //--------------------------------------------------------
	        // Correspondence CSV Evaluation
	        //--------------------------------------------------------
	        ExecutionResultSet allExecutionResults = new ExecutionResultSet();
	        allExecutionResults.addAll(executionResultSetConference);
	        EvaluatorCSV evaluatorCSV = new EvaluatorCSV(allExecutionResults);
	        evaluatorCSV.writeToDirectory();
		}catch(Exception e) {
			System.out.println("Exception!");
		}
		
	}

}
