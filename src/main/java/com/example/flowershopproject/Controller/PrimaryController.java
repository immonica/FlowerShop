package com.example.flowershopproject.Controller;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
//
//	@FXML
//	private ProgressBar progressBar;
//
//	@FXML
//	private Button runButton;
//
//	@FXML
//	private TextField workload;
//
//	@FXML
//	private ChoiceBox nrThreads;
//
////	public String getSeqWriteResult() {
////		return seqWriteResult;
////	}
////
////	public String getSeqReadResult() {
////		return seqReadResult;
////	}
////
////	public String getRandWriteResult() {
////		return randWriteResult;
////	}
////
////	public String getRandReadResult() {
////		return randReadResult;
////	}
//
//	private long  NumberThreads;
//	private long  Workload;
//
//	public long getNumberThreads() {
//
//		if (nrThreads.equals("16")) {
//			NumberThreads = 16;
//		} else if (nrThreads.equals("32")) {
//			NumberThreads = 32;
//		} else if (nrThreads.equals("64")) {
//			NumberThreads = 64;
//		} else if (nrThreads.equals("128")) {
//			NumberThreads = 128;
//		}
//
//		return NumberThreads;
//	}
//
//	public Long getWorkload() {
//		Workload = Long.parseLong(workload.getText());
//
//		return Workload;
//	}
//
//	//CPUThreadedRoots bench = new CPUThreadedRoots();
//
//	@FXML
//	private Label label;
//
//	@FXML
//	private ChoiceBox<String> partition, size;
//
//	private Task copyWorker;
//
////	@FXML
////	private void Run() {
////
////		progressBar.setProgress(0.0);
////		copyWorker = createWorker();
////
////		progressBar.progressProperty().unbind();
////		progressBar.progressProperty().bind(copyWorker.progressProperty());
//////		copyWorker.messageProperty().addListener((observable, oldValue, newValue) -> System.out.println(newValue));
//////		copyWorker.messageProperty().addListener((observable, oldValue, newValue) -> label.setText(newValue));
////
////		copyWorker.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
////			@Override
////			public void handle(WorkerStateEvent workerStateEvent) {
////				try {
////					AlertBox.display("Finished", "Press the button to see the results");
////				} catch (IOException e) {
////					e.printStackTrace();
////				}
////			}
////		});
////
////		new Thread(copyWorker).start();
////
////		stringPartition = partition.getValue();
////		stringSize = size.getValue();
////
////		long tempSize = getStringSize();
////		String tempPartition = getStringPartition();
////
////		System.out.println(tempPartition);
////		System.out.println(tempSize);
////
////		IBenchmark seqWrite = new HDDSeqWriteSpeed();
////		IBenchmark seqRead = new HDDSeqReadSpeed();
////
////		try {
////			seqWrite.initialize(tempPartition, tempSize);
////			seqWrite.warmUp();
////			seqWrite.run();
////
////			seqWriteResult = seqWrite.getResult();
////		} catch (IOException e) {
////			e.printStackTrace();
////		}
////		try {
////			seqRead.initialize(tempPartition, tempSize);
////			seqRead.warmUp();
////			seqRead.run();
////
////			seqReadResult = seqRead.getResult();
////		} catch (IOException e) {
////			e.printStackTrace();
////		}
////		IBenchmark randWrite = new HDDRandWriteSpeed();
////		IBenchmark randRead = new HDDRandReadSpeed();
////		try {
////			randWrite.initialize(tempPartition, tempSize);
////			randWrite.warmUp();
////			randWrite.run();
////
////			randWriteResult = randWrite.getResult();
////		} catch (IOException e) {
////			e.printStackTrace();
////		}
////		try {
////			randRead.initialize(tempPartition, tempSize);
////			randRead.warmUp();
////			randRead.run();
////
////			randReadResult = randRead.getResult();
////		} catch (IOException e) {
////			e.printStackTrace();
////		}
////
////		try {
////			FileWriter myWriter = new FileWriter("results.txt");
////			myWriter.write(seqReadResult + "\n" + seqWriteResult + "\n" + randReadResult + "\n" + randWriteResult);
////			myWriter.close();
////		} catch (IOException e) {
////			System.out.println("An error occurred.");
////			e.printStackTrace();
////		}
////
////		System.out.println(tempPartition);
////		System.out.println(tempSize);
////
////	}
//
//
//	public void initialize(URL url, ResourceBundle rb) {
//
//		nrThreads.getItems().removeAll();
//		nrThreads.getItems().addAll("16", "32", "64", "128");
//		nrThreads.getSelectionModel().select("16");
//	}
//
////	public Task createWorker() {
////
//////		return new Task() {
//////
//////			@Override
//////			protected Object call() throws Exception {
//////				for (int i = 0; i <= 100; i++) {
//////					if (i == 0)
//////						Thread.sleep(1000);
//////					// Thread.sleep(100);
//////					Thread.sleep(50);
//////					updateMessage("Task Completed : " + (i) + "%");
//////					updateProgress(i, 100);
//////
//////				}
////				return true;
//////			}
//////		};
////	}

}