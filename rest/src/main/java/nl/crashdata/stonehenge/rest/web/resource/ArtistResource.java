package nl.crashdata.stonehenge.rest.web.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import nl.crashdata.stonehenge.rest.web.model.ArtistPrimer;

@Path("artist")
public class ArtistResource
{
	@GET
	@Path("${id}")
	public ArtistPrimer get(@PathParam("id") Long id)
	{
		return null;
	}
}
