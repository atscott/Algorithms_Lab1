package main.java.scottat;

import com.google.common.base.Joiner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * User: atscott
 * Date: 3/13/14
 * Time: 6:25 PM
 */
public class CsvFileWriter
{
  String fileName;

  public CsvFileWriter(String fileName)
  {
    this.fileName = fileName;
  }

  public void WriteHeader(String... headers) throws IOException
  {
    File file = new File(fileName);
    if (file.exists())
      return;
    WriteResults(headers);
  }

  public void WriteResults(Object... results) throws IOException
  {
    File file = new File(fileName);
    FileWriter fileWriter = new FileWriter(file, true);
    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
    String dataLine = Joiner.on(",").join(results);
    fileWriter.append("\n");
    fileWriter.append(dataLine);
    bufferedWriter.close();
  }
}
