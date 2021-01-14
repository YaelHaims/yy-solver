package server_side;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
public class MyClientHandler implements ClientHandler {

	@Override
	public void handleClient(InputStream input, OutputStream output) {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(input));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(output)), true);
		try {
			List<List<Double>> lines = new ArrayList<List<Double>>();
			String line = in.readLine();
			
			while(!line.equals("end")) {
				lines.add(Arrays.asList(line.split(","))
					 .stream()
					 .map(s -> Double.parseDouble(s))
					 .collect(Collectors.toList()));
				line = in.readLine();
			}
			
			String s = in.readLine();
			
			String[] entryLocation = s.split(",");
			int entryRow = Integer.parseInt(entryLocation[0]);
			int entryCol = Integer.parseInt(entryLocation[1]);
			BoardCell entry = new BoardCell(entryRow, entryCol, lines.get(entryRow).get(entryCol));
			
			s = in.readLine();
			String[] exitLocation = s.split(",");
			int exitRow = Integer.parseInt(exitLocation[0]);
			int exitCol = Integer.parseInt(exitLocation[1]);
			BoardCell exit = new BoardCell(exitRow, exitCol, lines.get(exitRow).get(exitCol));
			
			SearchableBoard board = new SearchableBoard(lines, entry, exit);
			BestFirstSearcher searcher = new BestFirstSearcher();
			Solution solution = searcher.search(board);
			out.println(solution.toString());
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
