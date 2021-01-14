package server_side;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class MyTestClientHandler implements ClientHandler {

	private CacheManager<String, String> cacheManager;
	private Solver<String, String> solver;

	public MyTestClientHandler(Solver<String, String> solver, CacheManager<String, String> cacheManager) {
		this.cacheManager = cacheManager;
		this.solver = solver;
	}

	@Override
	public void handleClient(InputStream inputStream, OutputStream outputStream) {
		PrintWriter out = new PrintWriter(outputStream);
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
		String problem;

		try {
			while ((problem = in.readLine()) != null && !problem.equals("end")) {
				String solution = null;
				if (cacheManager.isSolutionCached(problem)) {
					solution = cacheManager.getCachedSolution(problem);
				} else {
					solution = solver.solve(problem);
					cacheManager.saveSolution(problem, solution);
				}

				out.println(solution);
				out.flush();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
