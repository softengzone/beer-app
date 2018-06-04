-- inserting brewery details
insert into brewery (brewery_id, name, email, phone, address1, address2, address3, country, post_code) 
	values (10001, 'Eight Degrees Brewing', 'accounts@eightdegrees.ie', '025 84933', 'Unit 3, Coolnanave Industrial Park', 'Mitchelstown', 'Co Cork', 'P67 RW84', 'Ireland');
insert into brewery (brewery_id, name, email, phone, address1, address2, address3, country, post_code)
	values (10002, 'Schneider Weisse', 'info@schneider-weisse.de', '0049 94 41/7 05-0', 'Emil-Ott-Str. 1-5', 'Kelheim', '', '93309', 'Germany');
insert into brewery (brewery_id, name, email, phone, address1, address2, address3, country, post_code)
	values (10003, 'St Austell Brewery', 'info@staustellbrewery.co.uk', '0345 2411122', '63 Trevarthian Road', 'St. Austell', 'Cornwall', 'PL25 4BY', 'United Kingdom');

-- inserting beers
insert into beer (name, description, brewery_id, abv) 
	values ('Barefoot Bohemian Pilsner Lager', 'Bohemians are creative dreamers who defy convention. Our Bohemian Pilsner is just that, an unorthodox lager with complex biscuity malt, soft rounded bitterness and a twist of spice from the noble Saaz and Hallertau hops. Treat as an aperitif, amazing with pizza or drink with Thai or Vietnamese food. A crisp and adventurous drop just perfect for a chilled out session. Bliss!', 10001, 4);
insert into beer (name, description, brewery_id, abv) 
	values ('Sunburnt Irish Red Ale', 'Like an Irishman on holiday in the Canaries, this beer has a red tint and a chilled out, mellow feel. Sunburnt Irish Red takes the characteristic sweet caramel flavours of an Irish red ale and adds additional malt complexity.', 10001, 5);
insert into beer (name, description, brewery_id, abv) 
	values ('Howling Gale Irish Pale Ale', 'Like blitzing down the Ballyhouras on your bike with an icy wind in your face, Howling Gale Irish Pale Ale delivers a refreshing crisp smack around the gills. It pours a pale golden colour, and has a pleasant grapefruit and citrus aroma. The malty base is well balanced by Cascade and Simcoe hops. Goes well with fish, chicken or anything off the barbecue. Howling Gale was the first beer we ever brewed, all the way back in 2011, and is still our most popular beer.', 10001, 5);
insert into beer (name, description, brewery_id, abv) 
	values ('Schneider Weisse Tap 6', 'For golden moments by the fireplace: "Mein Aventinus" - the wholehearted, dark ruby coloured wheat beer, intensive and fiery, warming, well-balanced and tender. Bavaria''s oldest wheat "Doppelbock" - brewed since 1907! Its sturdy body in combination with its sweet malty aroma is an invitation to profound indulgence - an ingenious blend with a strong body.  Perfectly matches rustic dishes, dark roasts and sweet desserts.', 10002, 8.2);
insert into beer (name, description, brewery_id, abv) 
	values ('St Austell Proper Job', 'A powerfully authentic IPA, Proper Job is brewed with a blend of imported American hops. It is a real treat of a beer with a growing reputation and is loved by beer enthusiasts far and wide. Perhaps the forerunner of all modern IPAs in the UK!', 10003, 5.5);