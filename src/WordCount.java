import java.io.IOException; 
import org.apache.hadoop.fs.Path; 
import org.apache.hadoop.io.IntWritable; 
import org.apache.hadoop.io.Text; 
import org.apache.hadoop.mapred.FileInputFormat; 
import org.apache.hadoop.mapred.FileOutputFormat; 
import org.apache.hadoop.mapred.JobClient; 
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.Partitioner;
import org.apache.hadoop.mapred.RunningJob;

public class WordCount { 

    public static void main(String[] args) throws IOException { 
        if (args.length != 2) { 
        System.err.println("Usage: WordCount <input dir> <output dir>"); 
        System.exit(-1); 
        } 
    
        JobConf conf = new JobConf(WordCount.class); 
        conf.setJobName("Word Count"); 
    
        FileInputFormat.setInputPaths(conf, new Path(args[0])); 
        FileOutputFormat.setOutputPath(conf, new Path(args[1])); 
    
        conf.setMapperClass(WordMapper.class); 
        conf.setReducerClass(SumReducer.class); 
        conf.setMapOutputKeyClass(Text.class); 
        conf.setMapOutputValueClass(IntWritable.class); 

        conf.setOutputKeyClass(Text.class); 
        conf.setOutputValueClass(IntWritable.class); 

        RunningJob job = JobClient.runJob(conf); 

    } 
} 

