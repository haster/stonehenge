package nl.crashdata.stonehenge.dao;

import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

@Transactional(value = TxType.MANDATORY)
@PersistenceContext(unitName = "persistence-unit")
public class ArtistDAO
{

}
