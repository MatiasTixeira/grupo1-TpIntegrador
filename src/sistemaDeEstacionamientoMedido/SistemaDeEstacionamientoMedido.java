package sistemaDeEstacionamientoMedido;

import java.time.LocalTime;

import sectorDeCompras.ISectorDeCompras;
import sectorDeEstacionamiento.ISectorDeEstacionamiento;
import sectorDeInfracciones.ISectorDeInfracciones;
import sectorDeSaldos.ISectorSaldo;

import sectorDeZonas.ISectorZonas;
import suscripcion.IGestorDeSuscripcion;

public class SistemaDeEstacionamientoMedido {
	
		private LocalTime horaInicio; 
		private LocalTime horaFin; 
		private Double precioPorHora;
		private ISectorDeEstacionamiento sectorEstacionamiento; 
		private ISectorSaldo sectorSaldo;
		private ISectorZonas sectorZona; 
		private ISectorDeCompras sectorCompra;
		private ISectorDeInfracciones sectorInfraccion;
		private IGestorDeSuscripcion sectorSuscripcion;
		

		public SistemaDeEstacionamientoMedido(LocalTime horaInicio, LocalTime horaFin, Double precioPorHora,
				ISectorDeEstacionamiento sectorEstacionamiento, ISectorSaldo sectorSaldo, ISectorZonas sectorZona,
				ISectorDeCompras sectorCompra, ISectorDeInfracciones sectorInfraccion,
				IGestorDeSuscripcion sectorSuscripcion) {
			super();
			this.setHoraInicio(horaInicio);
			this.setHoraFin(horaFin);
			this.setPrecioPorHora(precioPorHora);
			this.setSectorEstacionamiento(sectorEstacionamiento);
			this.setSectorSsaldol(sectorSaldo);
			this.setSectorZona(sectorZona);
			this.setSectorCompra(sectorCompra);
			this.setSectorInfraccion(sectorInfraccion);
			this.setSectorSuscripcion(sectorSuscripcion);
		}
		
		public LocalTime getHoraInicio() {
			return horaInicio;
		}
		public void setHoraInicio(LocalTime horaInicio) {
			this.horaInicio = horaInicio;
		}
		public LocalTime getHoraFin() {
			return horaFin;
		}
		public void setHoraFin(LocalTime horaFin) {
			this.horaFin = horaFin;
		}
		public Double getPrecioPorHora() {
			return precioPorHora;
		}
		public void setPrecioPorHora(Double precioPorHora) {
			this.precioPorHora = precioPorHora;
		}
		public ISectorDeEstacionamiento getSectorEstacionamiento() {
			return sectorEstacionamiento;
		}
		public void setSectorEstacionamiento(ISectorDeEstacionamiento sectorEstacionamiento) {
			this.sectorEstacionamiento = sectorEstacionamiento;
		}
		public ISectorSaldo getSectorSaldo() {
			return sectorSaldo;
		}
		public void setSectorSsaldol(ISectorSaldo sectorSsaldol) {
			this.sectorSaldo = sectorSsaldol;
		}
		public ISectorZonas getSectorZona() {
			return sectorZona;
		}
		public void setSectorZona(ISectorZonas sectorZona) {
			this.sectorZona = sectorZona;
		}
		public ISectorDeCompras getSectorCompra() {
			return sectorCompra;
		}
		public void setSectorCompra(ISectorDeCompras sectorCompra) {
			this.sectorCompra = sectorCompra;
		}
		public ISectorDeInfracciones getSectorInfraccion() {
			return sectorInfraccion;
		}
		public void setSectorInfraccion(ISectorDeInfracciones sectorInfraccion) {
			this.sectorInfraccion = sectorInfraccion;
		}
		public IGestorDeSuscripcion getSectorSuscripcion() {
			return sectorSuscripcion;
		}
		public void setSectorSuscripcion(IGestorDeSuscripcion sectorSuscripcion) {
			this.sectorSuscripcion = sectorSuscripcion;
		}
}
