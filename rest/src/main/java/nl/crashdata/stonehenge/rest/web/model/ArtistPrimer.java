package nl.crashdata.stonehenge.rest.web.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ArtistPrimer
{
	private String name;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
