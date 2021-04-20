import React from 'react';
import CoinGeckoTickerForm from './coinGeckoTickerForm.jsx';
import CoinGeckoInfoModal from './coinGeckoInfoModal.jsx';

class CoinGeckoTicker extends React.Component {
	
	constructor(props) {
		super(props);
		
		this.state = {
			data: {},
			isLoaded: false,
			error: null,
			
			priceData: {},
			priceDataLoaded: false,
			
			selectedGeckoId: null,
			showInfoModal: false
		}
		
		this.updateData = this.updateData.bind(this);

	}
	
	// update from child once a coin has been added to springboot database
	updateData(coin) {
		let obj = {name: coin.name, geckoId: coin.geckoId};
		let data = this.state.data;
		
		data._embedded.coinGeckoTickers.push(obj);
		
		this.setState({data:data});
	}
	
	// grab data from springboot database for which coins to display
	fetchData () {
		fetch("/api/coinGeckoTickers")
	    .then(res => res.json())
	    .then(
	      (result) => {	

		    this.setState({
	          isLoaded: true,
	          data: result
		
	        });
			
			// now that this has loaded, we can get data from coingecko
			// get data immediately and start an interval
			this.getCoinGeckoPriceData();
			this.interval = setInterval( () => {
				this.getCoinGeckoPriceData();
			}, 3000);
	      },
	      (error) => {
			console.log(error);
	        this.setState({
	          isLoaded: true,
	          error
	        });
	      }
	    )
	}
		
	getCoinGeckoPriceData() {
		let coins = "";
		this.state.data._embedded.coinGeckoTickers.map( (item, index) => 
			coins = coins + item.geckoId+","
		 );

	
		let url = "https://api.coingecko.com/api/v3/simple/price?ids="+coins+"&vs_currencies=usd%2Cbtc&include_24hr_change=true";
		
		fetch(url)
		    .then(res => res.json())
		    .then(
		      (result) => {	
			    this.setState({
		          priceDataLoaded: true,
		          priceData: result
			
		        });
	
		      },
		      (error) => {
				console.log(error);
		        this.setState({
		          priceDataLoaded: true,
		          error
		        });
		      }
		    )
	}
	
	showInfoModal = (event) => {
		let selectedGeckoId = event.currentTarget.attributes["data-hs-geckoid"].value;
		console.log(selectedGeckoId);
		this.setState({selectedGeckoId: selectedGeckoId, showInfoModal: true});
	}
	
	componentDidMount() {
		this.fetchData();
	}
	
	
	render() {
		const styleGreen = {
			color: "green"
		};
		
		const styleRed = {
			color: "red"
		};
		
		return (			

			<div id="coinGeckoTicker">
				<div className="card">
				  <div className="card-body">
				    <h5 className="card-title">Coin Gecko</h5>
					
					{
						this.state.priceDataLoaded ?			
							<table className="table table-sm">
								<thead>
									<tr>
								    	<th scope="col">Name</th>
								    	<th scope="col">USD</th>
								    	<th scope="col">BTC</th>
								    	<th scope="col">24h %</th>
									</tr>
								</thead>
								<tbody>
									{
										this.state.data._embedded.coinGeckoTickers.map( (item,index) =>
											<tr data-hs-geckoid={item.geckoId} data-bs-toggle="modal" data-bs-target="#geckoInfoModal" onClick={this.showInfoModal} key={item.geckoId}>
												<td>{item.name}</td>
												<td>{this.state.priceData[item.geckoId].usd.toFixed(2)}</td>
												<td>{this.state.priceData[item.geckoId].btc.toFixed(8)}</td>
												<td style={this.state.priceData[item.geckoId].btc_24h_change.toFixed(2) >= 0 ? styleGreen : styleRed}>{this.state.priceData[item.geckoId].btc_24h_change.toFixed(2)}%</td>
											</tr>
										)
									}
								</tbody>
							</table>	
							: <p>Get started using the Coin Gecko price ticker by adding a coin</p>}
							{
					
						this.state.showInfoModal ? 
							<CoinGeckoInfoModal geckoId={this.state.selectedGeckoId} />
							: ''
					}
				    <CoinGeckoTickerForm updateData={this.updateData}/>
				  </div>
				</div>
			</div>
		)
	}
	
}

export default CoinGeckoTicker;