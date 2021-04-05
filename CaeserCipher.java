// Jared Nechamkin 20079516
// Assignment 4: Caeser Cipher
// CS 2133

import java.io.*;
import java.util.*;
import java.security.AccessControlException;

public class CaeserCipher
{
	File input, output;
	int key;
	
	    public static void main(String[] args) 
		{
			File input, output;
			int key;
			boolean isEncryption = true;

			if(args.length != 4)
			{
				usageError();
			}

			output = new File(args[3]);
			key = Integer.parseInt(args[1]);

			if(args[0].equals("-e"))
			{
				isEncryption = true;
			} 
			else if (args[0].equals("-d"))
			{
				isEncryption = false;
			}
			else 
			{
				usageError();
			}

			try 
			{
				input = new File(args[2]);
				CaeserCipher caesar = new CaeserCipher(input, output, key);
				if(isEncryption)
				{
					caesar.fileEncrypt();
				} 
				else 
				{
					caesar.fileDecrypt();
				}
			}
			catch(AccessControlException e)
			{
				System.out.println("wrong input of either input or output");
			}
		}
	
	public CaeserCipher (File input, File output, int key)
	{
		this.input = input;
		this.output = output;
		this.key = key;
	}
	
	
	public void fileEncrypt()
	{
        try
		{
            Scanner scan = new Scanner(input);
            PrintWriter printWriter = new PrintWriter(output);

            while (scan.hasNextLine())
			{
                String tempInput = scan.nextLine();
                String tempEncrypt = lineEncrypt(tempInput, key);
                printWriter.write(tempEncrypt + "\n");
            }
            printWriter.flush();
            printWriter.close();

        } 
		catch (FileNotFoundException e)
		{
            System.out.println("file not found");
            System.exit(0);
        }
    }

    public void fileDecrypt()
	{
        try
		{
            Scanner scan = new Scanner(input);
            PrintWriter printWriter = new PrintWriter(output);

            while (scan.hasNextLine())
			{
                String tempInput = scan.nextLine();
                String tempDecrypt = lineDecrypt(tempInput, key);
                printWriter.write(tempDecrypt + "\n");
            }
            printWriter.flush();
            printWriter.close();

        } 
		catch (FileNotFoundException e)
		{
            System.out.println("file not found");
        }
    }


    public String lineEncrypt(String lineIn, int key)
	{
        String lineOut = "";
        for(int i = 0; i < lineIn.length(); i++)
		{
            char charTemp = lineIn.charAt(i);
            if (charTemp > 32 || charTemp < 126) 
			charTemp = (char)charTemp;
            charTemp = (char)(charTemp + key);
            if (charTemp > 126) 
			charTemp = (char)(charTemp - 95);
            if (charTemp < 32) 
			charTemp = (char)(charTemp  + 95);
            lineOut += charTemp;
        }
        return lineOut;
    }


    public String lineDecrypt(String lineIn, int key)
	{
        String lineOut = "";
        for(int i = 0; i < lineIn.length(); i++)
		{
            char charTemp = lineIn.charAt(i);
            if (charTemp > 32 || charTemp < 126) charTemp = (char)charTemp;
            charTemp = (char)(charTemp - key);
            if (charTemp < 32) charTemp = (char)(charTemp  + 95);
            if (charTemp > 126) charTemp = (char)(charTemp - 95);
            lineOut += charTemp;
        }

        return lineOut;
    }


    public static void usageError()
	{
        System.out.println("Usage: java Caesar [option: -e or -d] [key] [infile] [outfile]");
        System.out.println("Option Usage: -e for encryption; -d for decryption ");
        System.out.print("\n");
        System.exit(0);
    }

}